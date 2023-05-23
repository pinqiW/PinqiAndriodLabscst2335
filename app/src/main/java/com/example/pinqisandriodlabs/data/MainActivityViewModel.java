package com.example.pinqisandriodlabs.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//The Model - View - ViewModel pattern
// ViewModel - This is a class that stores all of the variables representing data that is shown on this page.
// So far in week 2, you just have 1 String which represents what the user has typed in the EditText.
// In Android, this class should inherit from ViewModel. We'll explain why next week.

//The reason for using a ViewModel(data) object separate from the AppCompatActivity(ui) is so that
// the variables are in a different object that doesn't get deleted on orientation changes.

public class MainActivityViewModel extends ViewModel {

    public String theText = "Hello World";

    public MutableLiveData<String> editString = new MutableLiveData<>();
    public MutableLiveData<Boolean> coffeeOrNot = new MutableLiveData<>();

    





}
