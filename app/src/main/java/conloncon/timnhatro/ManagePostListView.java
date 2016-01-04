package conloncon.timnhatro;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;



public class ManagePostListView extends ArrayAdapter<BaiDang>{
    Context context = null;
    int resource;
    List<BaiDang> objects = null;

    public ManagePostListView(Context context, int resource, List<BaiDang> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;

    }

    private class ViewHolder{
        TextView tvId, tvAdress,tvSquare,tvPrice,tvInfor,tvExtra_Infor;
        ImageButton ibDelete;
    }

    private int LayViTriBatDau(String chuoi,String chuoitimkiem){
        int vitri = 0;
        vitri = chuoi.indexOf(chuoitimkiem);
        return vitri;
    }
    //@Override

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, resource, null);
            holder.tvId = (TextView) convertView.findViewById(R.id.tvId);
            holder.tvAdress = (TextView) convertView.findViewById(R.id.tvAdress1);
            holder.tvSquare = (TextView) convertView.findViewById(R.id.tvSquare1);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tvPrice1);
            holder.tvInfor = (TextView) convertView.findViewById(R.id.tvInfo1);
            holder.tvExtra_Infor=(TextView) convertView.findViewById(R.id.tvExtraInfo1);
            //holder.ibDelete = (ImageButton) convertView.findViewById(R.id.ibDelete1);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        BaiDang item = objects.get(position);

        holder.tvId.setText(item.getId().toString());
        holder.tvAdress.setText(item.getAdress().toString());
        holder.tvSquare.setText(item.getSquare().toString());
        holder.tvPrice.setText(item.getPrice().toString());
        holder.tvInfor.setText(item.getInfor().toString());
        holder.tvExtra_Infor.setText(item.getExtra_infor().toString());
        /*
        if(MainActivity.chuoiTimKiem != null){
            SpannableString textSpan = new SpannableString(item.getAdress());

            int vitribatdau = LayViTriBatDau(item.getAdress(), MainActivity.chuoiTimKiem);
            int vitriketthuc = MainActivity.chuoiTimKiem.length();
            textSpan.setSpan(new BackgroundColorSpan(Color.RED), vitribatdau, vitribatdau + vitriketthuc, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.tvAdress.setText(textSpan);
        }else{
            holder.tvAdress.setText(item.getAdress());
        }
        */


//		View view = View.inflate(context, resource, null);
//		TextView tvId = (TextView) view.findViewById(R.id.tvID);
//		TextView tvTenBaiHat = (TextView) view.findViewById(R.id.tvTenBaiHat);
//		TextView tvLoiBaiHat = (TextView) view.findViewById(R.id.tvLoi);
//
//		Song item = objects.get(position);
//		tvId.setText(item.getId().toString());
//		tvTenBaiHat.setText(item.getSongname().toString());
//		tvLoiBaiHat.setText(item.getLyric().toString());

        return convertView;
    }




}
