package com.example.pinqisandriodlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pinqisandriodlabs.databinding.ActivityChatRoomBinding;
import com.example.pinqisandriodlabs.databinding.ReceiveMessageBinding;
import com.example.pinqisandriodlabs.databinding.SentMessageBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatRoom extends AppCompatActivity {

    protected ArrayList<ChatMessage> messages = new ArrayList<>();

    protected Button myButton;
    protected Button receButton;
    protected RecyclerView recyclerView;


    /** This holds the edit text for typing into */
    protected EditText theTextInput;
    ChatRoomViewModel chatModel;
    RecyclerView.Adapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();
        if(messages == null)
        {
            chatModel.messages.postValue( messages = new ArrayList<>());
        }
        ActivityChatRoomBinding binding = ActivityChatRoomBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        myButton = binding.sendButton;
        receButton = binding.receButton;
        theTextInput = binding.textInput;
        recyclerView = binding.recycleView;

        myButton.setOnClickListener(click -> {
            String input = theTextInput.getText().toString();

            boolean type = true;
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());

            //insert into ArrayList
            messages.add(new ChatMessage(input, currentDateandTime, type));

            myAdapter.notifyItemInserted(messages.size()); //updates the rows
//            myAdapter.notifyDataSetChanged(); //updates the rows

            //clear input
            theTextInput.setText("");
            System.out.println("clicked send button");
        });

        receButton.setOnClickListener(click ->{
            String input = theTextInput.getText().toString();
            System.out.println("clicked receive button");
            boolean type = false;
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());

            //insert into ArrayList
            messages.add(new ChatMessage(input, currentDateandTime, type));


            myAdapter.notifyItemInserted(messages.size()); //updates the rows
//            myAdapter.notifyDataSetChanged(); //updates the rows


            //clear input
            theTextInput.setText("");

        });


        /**
         * A RecyclerView.Adapter object needs 3 functions to tell the view how to draw items in the list.
         */
        recyclerView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                //this inflates the row layout

                //int viewType is what layout to load
                if(viewType == 0) {
                    SentMessageBinding binding =                           //how big is parent?
                            SentMessageBinding.inflate(getLayoutInflater(), parent, false);

                    return new MyRowHolder(binding.getRoot());
                }
                else {
                    ReceiveMessageBinding binding =
                            ReceiveMessageBinding.inflate(getLayoutInflater(), parent, false);
                    return new MyRowHolder(binding.getRoot());
                }
            }


            /**
             * This initializes a ViewHolder to go at the row specified by the position parameter
             * @param holder The ViewHolder which should be updated to represent the contents of the
             *        item at the given position in the data set.
             * @param position The position of the item within the adapter's data set.
             */
            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                //update the widgets:
                ChatMessage atThisRow = messages.get(position);
                Log.d("ChatRoom", "Position: " + position + ", Message: " + atThisRow.message + ", Time: " + atThisRow.timeSent + ", Type: " + atThisRow.sendOrReceive);
                holder.timeText.setText(atThisRow.timeSent);
                holder.timeText.setText(atThisRow.timeSent);
                holder.messageText.setText(atThisRow.message);//puts the string in position at theWord TextView
            }

            /**
             * This function just returns an int specifying how many items to draw.
             * @return
             */
            @Override
            public int getItemCount() {
                return messages.size();
            }

            /**
             * load different layouts for different rows, this function lets you return an int to indicate which layout to load. For now, we are just loading one layout, so you can just return 0 for this function:
             * @param position position to query
             * @return an int which is the parameter which gets passed in to the onCreateViewHolder(ViewGroup parent, int viewType) function
             */
            @Override
            public int getItemViewType(int position){
                if(messages.get(position).sendOrReceive)
                {
                    return 0;
                }
                else
                {
                    return 1;
                }
            }
        });

        /**
         * LayoutManager
         * The RecyclerView supports 1 or more columns for showing data, and you can either scroll in a Vertical or Horizontal direction through the items.
         * To specify a single column scrolling in a Vertical direction, you call:
         */
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }



    //        The whole point of the MyRowHolder class is to maintain variables for what you want to set on each row in your list.
    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;

        // The view that is passed in as a parameter represents the ConstraintLayout that is the root of the row.
        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.message);
            timeText = itemView.findViewById(R.id.time);
        }
    }

}