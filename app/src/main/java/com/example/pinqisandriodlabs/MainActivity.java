package com.example.pinqisandriodlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/** This is main page for a simple password checker app
 * @author PinqiW
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity {


    /** This holds the text at the centre of the scree **/
    TextView tv = null;

    /** This holds the text at the centre of the scree **/
    EditText et = null;

    /** This holds the button at the centre of the scree **/
    Button btn = null;


    // equivalent to    static void main(String args[])
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // loads an XML file on the page
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        et = findViewById(R.id.editTextText);
        btn = findViewById(R.id.button);

        btn.setOnClickListener((v) -> {
            String pswd = et.getText().toString();
            checkPasswordComplexity( pswd);
            if(checkPasswordComplexity(pswd)){
                tv.setText("Your password meets the requirements.");
            }else{
                tv.setText("Your password shall not pass!");
            }
        });
    }


    /**
     * method checks if the character is any of the special character
     * @param chara which is the character that's being checked
     * @return return true if the character is one of  special character
     */
    boolean isSpecialCharacter(char chara){
        switch (chara){
            case '#':
            case '?':
            case '*':
            case '$':
            case '%':
            case '^':
            case '&':
            case '@':
            case '!':
                return true;
            default:
                return false;
        }

    }

    /**
     * method checks if this string has an Upper Case letter, a lower case letter, a number, and a special symbol (#$%^&*!@?).
     * @param pswd The String object that we are checking
     * @return  Return true if the password entered meet all four criteria, otherwise return false
     */
    private boolean checkPasswordComplexity(String pswd) {
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

        for(int i = 0; i < pswd.length();i++){
            char c = pswd.charAt(i);
            if (Character.isDigit(c)) {
                foundNumber = true;
            }

            if (Character.isUpperCase(c)) {
                foundUpperCase = true;
            }

            if (Character.isLowerCase(c)) {
               foundLowerCase = true;
            }
            if(isSpecialCharacter(c)){
                foundSpecial = true;
            }

            if (foundUpperCase && foundLowerCase && foundNumber && foundSpecial) {
                return true;
            }

        }

        if(!foundUpperCase)
        {
            Toast.makeText( this,"missing an upper case letter", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if(!foundLowerCase)
        {
            Toast.makeText( this,"missing an lower case letter", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if(!foundNumber) {
            Toast.makeText( this,"missing a number", Toast.LENGTH_SHORT).show();
        }

        else if(!foundSpecial) {
            Toast.makeText( this,"missing a special character", Toast.LENGTH_SHORT).show();
        }
        else
            return  true; //only get here if they're all true


        return false;

    }




}