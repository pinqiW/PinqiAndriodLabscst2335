package com.example.pinqisandriodlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pinqisandriodlabs.databinding.ActivityChatRoomBinding;
import com.example.pinqisandriodlabs.databinding.SentMessageBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChatRoom extends AppCompatActivity {

    ActivityChatRoomBinding binding;
    ArrayList<String> messages = new ArrayList<>();

    private RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_chat_room);
        setContentView(binding.getRoot());

        binding.sendButton.setOnClickListener(click -> {
            messages.add(binding.textInput.getText().toString());
            myAdapter.notifyItemInserted(messages.size()-1);

            //clear the previous text:
            binding.textInput.setText("");
        });

//        The whole point of the MyRowHolder class is to maintain variables for what you want to set on each row in your list.
        class MyRowHolder extends RecyclerView.ViewHolder {
            TextView messageText;
            TextView timeText;

            // The view that is passed in as a parameter represents the ConstraintLayout that is the root of the row.
            public MyRowHolder(@NonNull View itemView) {
//                This view has the two TextViews as sub-items so you have to find them by id, like you would normally do for an Activity in the onCreate function. Modify your constructor:
                super(itemView);
                messageText = itemView.findViewById(R.id.message);
                timeText = itemView.findViewById(R.id.time);
            }
        }

        /**
         * A RecyclerView.Adapter object needs 3 functions to tell the view how to draw items in the list.
         */
        binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull

            /**
             *  This function creates a ViewHolder object which we'll learn next. It represents a single row in the list
             *  This is responsible for creating a layout for a row, and setting the TextViews in code
             */
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                return new MyRowHolder(binding.getRoot());
            }

            /**
             * This initializes a ViewHolder to go at the row specified by the position parameter
             * @param holder The ViewHolder which should be updated to represent the contents of the
             *        item at the given position in the data set.
             * @param position The position of the item within the adapter's data set.
             */
            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                holder.messageText.setText("");
                holder.timeText.setText("");
                String obj = messages.get(position);
                holder.messageText.setText(obj);
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
                return 0;
            }

        });

    }
}