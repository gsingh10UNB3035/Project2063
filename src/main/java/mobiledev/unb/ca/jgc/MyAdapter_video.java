package mobiledev.unb.ca.jgc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mobiledev.unb.ca.jgc.model.VideoInfo;


public class MyAdapter_video extends RecyclerView.Adapter<MyAdapter_video.ViewHolder> {
    private final static String TAG = "MyAdapter";
    private final List<VideoInfo> dataset;
    private final AppCompatActivity parentActivity;
    private final SharedPreferences sharedPreferences;

    public MyAdapter_video(AppCompatActivity parentActivity, List<VideoInfo> myDataset, SharedPreferences sharedPreferences) {
        this.parentActivity = parentActivity;
        dataset = myDataset;
        this.sharedPreferences = sharedPreferences;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public CheckBox mCheckBox;

        public ViewHolder(LinearLayout v) {
            super(v);
            mTextView = v.findViewById(R.id.item_video_textview);
            mCheckBox = v.findViewById(R.id.item_video_checkbox);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_layout_audio, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // TODO
        //  Get the item at index position in mDataSet
        final VideoInfo item = dataset.get(position);

        // TODO
        //  Set the TextView in the ViewHolder to be the title attribute
        holder.mTextView.setText(item.getTitle());
        // TODO
        //  Set the onClickListener for the TextView in the ViewHolder such
        //  that when it is clicked, it creates an explicit intent to launch DetailActivity
        //  with extra pieces of information in this intent.
        //set play music
        /*holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentActivity, DetailActivity.class);
                intent.putExtra("GameTitle",item.getTitle());
                intent.putExtra("GameNumber",item.getNumber());
                intent.putExtra("GameDates",item.getDates());
                intent.putExtra("GameYear",item.getYear());
                intent.putExtra("GameHostCity",item.getHostCity());
                intent.putExtra("GameWikipediaLink",item.getWikipediaLink());
                parentActivity.startActivity(intent);
            }
        });*/
        // TODO: SharedPreference
        //  Set the CheckBox in the ViewHolder (holder) to be checked if the
        //  value stored in the shared preferences for the number for this GamesInfo is true, and to
        //  be not checked otherwise; if there is no value in the shared
        //  preferences for this id, then the checkbox should not be checked
        //  (i.e., assume a default value of false for anything not in
        //  the shared preferences).
        // Hints:
        // https://developer.android.com/reference/android/content/SharedPreferences.html#getBoolean(java.lang.String,%20boolean)
        // https://developer.android.com/reference/android/widget/CheckBox.html
        // https://developer.android.com/reference/android/widget/CompoundButton.html#setChecked(boolean)//
        holder.mCheckBox.setChecked(false);
        if(sharedPreferences.getBoolean(item.getNumber(), false) == true) {
            holder.mCheckBox.setChecked(true);
        }
        else {
            holder.mCheckBox.setChecked(false);
        }
        // This method is called when a CheckBox is clicked, and its status
        // changes from checked to not checked, or from not checked to checked.
        // isChecked will be true if the CheckBox is now checked, and false if
        // the CheckBox is now not checked.
        holder.mCheckBox.setOnCheckedChangeListener(
                (v, isChecked) -> {
                    // TODO: SharedPreferences
                    //  Get a SharedPreferences.Editor for SharedPreferences
                    //  Hint: https://developer.android.com/reference/android/content/SharedPreferences.html#edit()
                    SharedPreferences.Editor edit=sharedPreferences.edit();
                    // TODO: Shared Preferences
                    //  Set the value stored in SharedPreferences for the number for this GamesInfo to be
                    //  the value of isChecked
                    //  Hint: https://developer.android.com/reference/android/content/SharedPreferences.Editor.html#putBoolean(java.lang.String,%20boolean)
                    edit.putBoolean(item.getNumber(), holder.mCheckBox.isChecked());
                    // TODO: SharedPreferences
                    //  Apply the changes from this editor
                    //  Hint: https://developer.android.com/reference/android/content/SharedPreferences.Editor.html#commit()
                    edit.commit();
                }
        );
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        holder.mCheckBox.setOnCheckedChangeListener(null);
        super.onViewRecycled(holder);
    }
}
