package com.santosdaniel.mymechanicagenda.presenter.customerDetails

import android.app.Activity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar

import com.santosdaniel.mymechanicagenda.helper.DBHelper
import com.santosdaniel.mymechanicagenda.model.database.Vehicle
import com.santosdaniel.mymechanicagenda.presenter.GenericRViewCursorAdapter
import com.santosdaniel.mymechanicagenda.view.viewModel.GenericListItem

/**
 * Adapter of the list of vehicles
 */
class VehiclesAdapter constructor(activity: Activity, recyclerView: RecyclerView, progressBar: ProgressBar)
    : GenericRViewCursorAdapter<Vehicle>(activity, recyclerView, progressBar, VehiclesAdapter.VehiclesDiff) {

    /**
     *
     * @param data          reference to the item to fill the elements
     * @param itemData        cursor from where the data should be fetch
     *
     * @return The GenericListItem associated with current cursor
     */
    override fun fillItemData(data: GenericListItem, itemData: Vehicle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Called when the user clicks in one item of the list
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View) {
        val item = getItemByView(v)
        if (item != null && !DBHelper.validId(item.id)) {
            /*
            val intent =  Intent(activity, CustomerDetailsActivity::class.java)
            intent.putExtra(CustomerDetailsActivity.LOOKUP_KEY, item.lookUpKey)
            intent.putExtra(CustomerDetailsActivity.TITLE_KEY, item.title)
            intent.putExtra(CustomerDetailsActivity.IMAGE_URI_KEY, item.imageUri)
            IntentHelper.startNewActivity(activity, v, intent)
            */
        }
    }

    /**
     * Should be used by the activity to indicate that is able to receive content
     */
    override fun startLoading() {

    }

    /**
     * Should be used by the activity to indicate that is able no able to receive content
     * anymore
     */
    override fun stopLoading() {

    }

    fun setVehicles(vehicles: List<Vehicle>?) {}

    /*
    private NoteClickListener clickListener;
    private List<Vehicle> dataset;
    */

    /*
    public interface NoteClickListener {
        void onNoteClick(int position);
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        public TextView text;
        public TextView comment;

        public NoteViewHolder(View itemView, final NoteClickListener clickListener) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.textViewNoteText);
            comment = (TextView) itemView.findViewById(R.id.textViewNoteComment);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onNoteClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public VehiclesAdapter(NoteClickListener clickListener) {
        this.clickListener = clickListener;
        this.dataset = new ArrayList<>();
    }

    public void setVehicles(@NonNull List<Vehicle> vehicles) {
        dataset = vehicles;
        notifyDataSetChanged();
    }

    public Vehicle getNote(int position) {
        return dataset.get(position);
    }

    @Override
    public VehiclesAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(VehiclesAdapter.NoteViewHolder holder, int position) {
        Vehicle vehicle = dataset.get(position);
        holder.text.setText(vehicle.get.getText());
        holder.comment.setText(vehicle.getComment());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
    */

    companion object {
        private val VehiclesDiff = object : DiffUtil.ItemCallback<Vehicle>() {
            override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
