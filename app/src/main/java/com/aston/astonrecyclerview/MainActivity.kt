package com.aston.astonrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aston.astonrecyclerview.data.Contact
import com.aston.astonrecyclerview.databinding.ActivityMainBinding
import com.aston.astonrecyclerview.model.RecyclerViewModel
import com.aston.astonrecyclerview.recycler.MyRecyclerViewAdapter
import com.aston.astonrecyclerview.repository.RecyclerViewRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.apache.tools.ant.taskdefs.Concat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerViewModel: RecyclerViewModel
    lateinit var myRecyclerViewAdapter: MyRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        val viewModelFactory = RecyclerViewModelFactory(RecyclerViewRepository())
        recyclerViewModel = ViewModelProvider(this, viewModelFactory).get(RecyclerViewModel::class.java)

         myRecyclerViewAdapter = MyRecyclerViewAdapter { contact, position, checkBox ->
            if(recyclerViewModel.getTrashOnClickBoolean()){
                checkBox.visibility = View.VISIBLE
                recyclerViewModel.addToDeleteList(contact)
                //recyclerViewModel.addToDeletePositionList(position)
                checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked){
                        recyclerViewModel.addToDeleteList(contact)
                      //  recyclerViewModel.addToDeletePositionList(contact)
                    } else{
                        recyclerViewModel.removeToDeleteList(contact)
                       // recyclerViewModel.removeToDeletePositionList(contact)

                    }
                }
            } else {
                //create AlertDialog and Modification concat
                createAlertDialog(contact, position)

            }
        }
        binding.mainActivityRecyclerView.adapter = myRecyclerViewAdapter

        val repComtacts = recyclerViewModel.getContacts()

        binding.mainActivityRecyclerView.layoutManager = LinearLayoutManager(this)

        myRecyclerViewAdapter.submitList(repComtacts)

        binding.appCompatImageButton.setOnClickListener {
            recyclerViewModel.trashOnClickTrue()
            binding.mainActiviytyButtonAddContact.visibility = View.INVISIBLE
            binding.mainActiviytyButtonDelete.visibility = View.VISIBLE
            binding.mainActiviytyButtonCancel.visibility = View.VISIBLE
        }

        binding.mainActiviytyButtonCancel.setOnClickListener {
            recyclerViewModel.trashOnClickFalse()
            binding.mainActiviytyButtonAddContact.visibility = View.VISIBLE
            binding.mainActiviytyButtonDelete.visibility = View.INVISIBLE
            binding.mainActiviytyButtonCancel.visibility = View.INVISIBLE
        }

        binding.mainActiviytyButtonDelete.setOnClickListener {
            recyclerViewModel.trashOnClickFalse()
            binding.mainActiviytyButtonAddContact.visibility = View.VISIBLE
            binding.mainActiviytyButtonDelete.visibility = View.INVISIBLE
            binding.mainActiviytyButtonCancel.visibility = View.INVISIBLE



            val newList = recyclerViewModel.removeContactFromDelete()
            myRecyclerViewAdapter.submitList(newList)

            recyclerViewModel.saveNewList(newList)

        }

        binding.mainActiviytyButtonAddContact.setOnClickListener {
            val newAddInt = recyclerViewModel.getContacts().size
            val newContact = Contact(name = "", surname = "", phoneNumber = "", id = newAddInt)
            createAlertDialog(newContact, null)
        }
    }

    fun createAlertDialog(contact: Contact, position: Int?){
        val dialog = MaterialAlertDialogBuilder(this)
        val customLayout = layoutInflater.inflate(R.layout.concat_dialog, null)
        val cancelButton: Button = customLayout.findViewById(R.id.concat_dialog_button_cancel)
        val changeButton: Button = customLayout.findViewById(R.id.concat_dialog_button_change)
        val nameEditText: EditText = customLayout.findViewById(R.id.concat_dialog_edit_text_name)
        val surnameEditText: EditText = customLayout.findViewById(R.id.concat_dialog_edit_text_surname)
        val phoneNumberEditText: EditText = customLayout.findViewById(R.id.concat_dialog_edit_text_phone_number)

        nameEditText.setText(contact.name.toString())
        surnameEditText.setText(contact.surname)
        phoneNumberEditText.setText(contact.phoneNumber)



        dialog.setView(customLayout)
        dialog.create()
       val alertDialog = dialog.show()

        changeButton.setOnClickListener {

            val newContact = Contact(id = contact.id, name = nameEditText.text.toString(), surname = surnameEditText.text.toString(), phoneNumber = phoneNumberEditText.text.toString(), checked = false )

            if (position!= null) {
                val newList = recyclerViewModel.changeContact(newContact, position)

                myRecyclerViewAdapter.submitList(newList)


                recyclerViewModel.saveNewList(newList)


                alertDialog.dismiss()
            } else{
                val newList = recyclerViewModel.addContact(newContact)
                myRecyclerViewAdapter.submitList(newList)


                recyclerViewModel.saveNewList(newList)
                alertDialog.dismiss()
            }


        }


        cancelButton.setOnClickListener {


            alertDialog.dismiss()
        }




    }

}