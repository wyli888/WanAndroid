package com.e.wanandroid.ui.splash;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.wanandroid.R;

import java.util.List;

public class OnBoardAdapter extends RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder>{

  public OnBoardAdapter(List<OnboardItem> onBoardItems) {
    this.onBoardItems = onBoardItems;
  }

  private final List<OnboardItem> onBoardItems;

  @NonNull
  @Override
  public OnBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new OnBoardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_onboarding, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull OnBoardViewHolder holder, int position) {
    holder.setDate(onBoardItems.get(position));
  }

  @Override
  public int getItemCount() {
    return onBoardItems.size();
  }

  static class OnBoardViewHolder extends RecyclerView.ViewHolder {

    private final TextView textTitle;
    private final ImageView imageView;
    OnBoardViewHolder(@NonNull View itemView) {
      super(itemView);
      textTitle = itemView.findViewById(R.id.textTitle);
      imageView = itemView.findViewById(R.id.imageOnBorading);
    }

    void setDate(OnboardItem item){
      textTitle.setText(item.getTitle());
      imageView.setImageResource(item.getImage());
    }



  }



}
