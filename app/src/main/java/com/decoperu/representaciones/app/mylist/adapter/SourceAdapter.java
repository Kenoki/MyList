package com.decoperu.representaciones.app.mylist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.decoperu.representaciones.app.mylist.R;
import com.decoperu.representaciones.app.mylist.model.Source;
import com.decoperu.representaciones.app.mylist.model.SourceResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aoki on 24/04/2017.
 */

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.ViewHolderSource>{

    List<Source> sources;
    NewsItemListener listener;

    public SourceAdapter(List<Source> sources, NewsItemListener listener) {
        this.sources = sources;
        this.listener = listener;
    }

    @Override
    public ViewHolderSource onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_source,parent,false);

        return new ViewHolderSource(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolderSource holder, int position) {
        Source source = sources.get(position);
        holder.idTextView.setText(source.getId());
        holder.nameTextView.setText(source.getName());
        holder.descriptionTextView.setText(source.getDescription());
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    public void replaceData(List<Source> _sources){
        this.sources = _sources;
        notifyDataSetChanged();
    }

    public class ViewHolderSource extends RecyclerView.ViewHolder
    {
        @BindView(R.id.idResorce)
        TextView idTextView;

        @BindView(R.id.nameResorce)
        TextView nameTextView;

        @BindView(R.id.descriptionResorce)
        TextView descriptionTextView;

        NewsItemListener listener;

        public ViewHolderSource(View itemView, final NewsItemListener listener) {
            super(itemView);

            ButterKnife.bind(this,itemView);
            this.listener = listener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.newsSelected(sources.get(position));
                }
            });
        }
    }

    public interface NewsItemListener{
        void newsSelected(Source _source);
    }
}
