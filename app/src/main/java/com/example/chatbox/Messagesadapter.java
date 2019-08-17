package com.example.chatbox;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class Messagesadapter implements ListAdapter {

    Context context;
    List<messages> Messages;
    LayoutInflater inflater;
    public Messagesadapter(Context context, List<messages> Messages){
        this.context=context;
        this.Messages=Messages;
        this.inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return Messages.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View layoutview = inflater.inflate(R.layout.messages, null);
        try {
            TextView from =layoutview.findViewById(R.id.From);
            TextView message = layoutview.findViewById(R.id.txtMessage);
            TextView to=layoutview.findViewById(R.id.to);
            messages Message= Messages.get(i);
            from.setText(Message.from);
            to.setText(Message.to);
            message.setText(Message.message);
            return layoutview;
        } catch (Exception ex) {
            Button button = new Button(context);
            button.setText("" + ex);
            return button;        }

    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
