package cr.ac.ucr.ecci.examen2.Adapter;

import com.squareup.picasso.Picasso;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cr.ac.ucr.ecci.examen2.DAO.Entities.User;
import cr.ac.ucr.ecci.examen2.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{
    List<User> users;
    LayoutInflater inflater;
    ItemClickListener listener;

    public void setListener(ItemClickListener listener){
        this.listener = listener;
    }

    public void setUsers(List<User> users){
        this.users = users;
        notifyDataSetChanged();
    }

    public User getUser(int index){ return this.users.get(index); }

    public void removeUserFromView(int index){
        this.users.remove(index);
        notifyDataSetChanged();
    }

    public UserAdapter(LayoutInflater inflater, List<User>users){
        setUsers(users);
        this.inflater = inflater;
    }


    @NonNull
    @Override
    public UserAdapter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserHolder(this.inflater.inflate(R.layout.user_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserHolder holder, int position) {
        holder.name.setText("Nombre: "+users.get(position).firstName + " " + users.get(position).lasttName);
        holder.userAge.setText("Edad: "+String.valueOf(users.get(position).age));
        holder.userEmail.setText("Email: "+users.get(position).email);
        holder.userPhone.setText("Teléfono: "+users.get(position).phone);
        Picasso.get().load(users.get(position).image).into(holder.userPic);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView userAge;
        TextView userPhone;
        TextView userEmail;
        ImageView userPic;
        public UserHolder(@NonNull View itemView) {
            super(itemView);
            userPic = itemView.findViewById(R.id.user_pic);
            name = itemView.findViewById(R.id.name);
            userAge = itemView.findViewById(R.id.age);
            userPhone = itemView.findViewById(R.id.phone);
            userEmail = itemView.findViewById(R.id.email);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(listener!=null) listener.onClick(getAdapterPosition());
        }
    }

    public interface ItemClickListener{
        public void onClick(int pos);
    }
}
