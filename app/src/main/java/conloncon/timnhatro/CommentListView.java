package conloncon.timnhatro;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;



public class CommentListView extends ArrayAdapter<Comment>{
    Context context = null;
    int resource;
    List<Comment> objects = null;

    public CommentListView(Context context, int resource, List<Comment> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;

    }

    private class ViewHolder{
        TextView textusername, textCmt, texttime;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, resource, null);
            holder.textusername = (TextView) convertView.findViewById(R.id.textusername);
            holder.textCmt = (TextView) convertView.findViewById(R.id.textCmt);
            holder.texttime = (TextView) convertView.findViewById(R.id.texttime);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Comment item = objects.get(position);

        holder.textusername.setText(item.getUsername().toString());
        holder.textCmt.setText(item.getComment().toString());
        holder.texttime.setText(item.getTime().toString());


        return convertView;
    }




}
