package com.hafizhamza.duskysolutiontask.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.hafizhamza.duskysolutiontask.Model.DawaiListModel;
import com.hafizhamza.duskysolutiontask.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class DawaiListAdapter extends RecyclerView.Adapter<DawaiListAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<DawaiListModel> Dawailist;
    private List<DawaiListModel> Dawailistfull;



    public class ViewHolder extends RecyclerView.ViewHolder {

       // public TextView countryName, presidentName, birthDate, rate;
        public TextView  Id_Name,Designation,EmpoymentType,Department, OfficialTiming;

        public CircleImageView photo, flag;
        public RelativeLayout layout;

        public ViewHolder(View view) {
            super(view);
            layout = (RelativeLayout) view.findViewById(R.id.layout);
            Id_Name = (TextView) view.findViewById(R.id.id_name_id);
            Designation = (TextView) view.findViewById(R.id.id_designations);
            EmpoymentType = (TextView) view.findViewById(R.id.id_EmploymentType);
            Department = (TextView) view.findViewById(R.id.id_Department);
            OfficialTiming = (TextView) view.findViewById(R.id.id_officialTime);

            photo = (CircleImageView) view.findViewById(R.id.president);

            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Toast.makeText(context, "Pressed", Toast.LENGTH_SHORT).show();
                }
            });

            layout.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Toast.makeText(context, "Pressed", Toast.LENGTH_SHORT).show();
                }
            });
            layout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    new AlertDialog.Builder(context).setIcon(android.R.drawable.ic_delete)
                            .setTitle("Are You Sure?")
                            .setMessage("Do You Really Want to Remove?")
                            .setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(context, "Thanks", Toast.LENGTH_SHORT).show();
                                }
                            }).setNegativeButton("Cancel",null).show();
                    Toast.makeText(context, "Long Pressed", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    new AlertDialog.Builder(context).setIcon(android.R.drawable.ic_delete)
                            .setTitle("Are You Sure?")
                            .setMessage("Do You Really Want to Remove?")
                            .setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(context, "Thanks", Toast.LENGTH_SHORT).show();
                                }
                            }).setNegativeButton("Cancel",null).show();
                    Toast.makeText(context, "Long Pressed", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }

    }

    public DawaiListAdapter(Context context, List<DawaiListModel> Dawailist) {
        this.context = context;
        this.Dawailist = Dawailist;
        Dawailistfull=new ArrayList<>(Dawailist);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_style, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        DawaiListModel dawai = Dawailist.get(position);

        holder.Id_Name.setText(dawai.getId().toString());
        holder.Designation.setText(dawai.getdesignation());
        holder.EmpoymentType.setText(dawai.getemployeementtype());
        holder.Department.setText(dawai.getdepartment());
        holder.OfficialTiming.setText(dawai.gettimein());
        Picasso.with(context).load(dawai.getPhoto()).into(holder.photo);

    }

    @Override
    public int getItemCount() {
        return Dawailist.size();
    }

    public void clear() {
        Dawailist.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<DawaiListModel> list) {
        Dawailist.addAll(list);
        notifyDataSetChanged();
    }

    public void add(DawaiListModel president) {
        Dawailist.add(president);
        notifyDataSetChanged();
    }
    @Override
    public Filter getFilter() {
        return ListFilter;
    }
    private Filter ListFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List FilterList=new ArrayList<>();
            if(charSequence ==null ||charSequence.length()==0)
            {
            FilterList.addAll(Dawailistfull);
            }
            else {
                String FilterPattern=charSequence.toString().toLowerCase().trim();
                for(DawaiListModel item:Dawailistfull)
                {
                if (item.getdesignation().toLowerCase().contains(FilterPattern))
                {
                    FilterList.add(item);
                }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=FilterList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            Dawailist.clear();
            Dawailist.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
