package sample.app.task.ui.itemType;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sample.app.task.utils.IconUtils;
import sample.app.task.R;
import sample.app.task.pojo.Option;

public class ItemTypeAdapter extends RecyclerView.Adapter<ItemTypeAdapter.ItemTypeViewHolder> {

    private ItemTypeClickListener itemTypeClickListener;
    private List<Option> optionList = new ArrayList<>(0);
    private Context context;

    public ItemTypeAdapter(ItemTypeClickListener itemTypeClickListener, Context context) {
        this.itemTypeClickListener = itemTypeClickListener;
        this.context = context;
    }

    public void updateList(List<Option> optionList) {
        this.optionList.clear();
        this.optionList.addAll(optionList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_type, viewGroup, false);
        return new ItemTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemTypeViewHolder propertyTypeViewHolder, int i) {
        Option option = optionList.get(i);
        propertyTypeViewHolder.bindIcon(option, i);
    }

    @Override
    public int getItemCount() {
        return optionList == null ? 0 : optionList.size();
    }

    public class ItemTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_type_root)
        RelativeLayout relativeLayout;
        @BindView(R.id.img_icon)
        ImageView iconImage;
        @BindView(R.id.type_name)
        TextView typeName;

        public ItemTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(ItemTypeViewHolder.this, itemView);
        }

        public void bindIcon(Option option, int position) {
            relativeLayout.setTag(position);
            String icon = option.getIcon();
            String name = option.getName();
            int iconDrawableInt = IconUtils.getIcon(icon);
            iconImage.setImageDrawable(context.getDrawable(iconDrawableInt));
            typeName.setText(name);
        }

        @Override
        public void onClick(View view) {
            int position = (int) view.getTag();
            Option option = optionList.get(position);
            itemTypeClickListener.onItemClicked(option);
        }
    }
}
