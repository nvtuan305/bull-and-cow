package com.goodlife.bullandcow.view.game;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goodlife.bullandcow.R;
import com.goodlife.bullandcow.model.GuessResult;

import java.util.ArrayList;

public class GuessResultAdapter extends
        RecyclerView.Adapter<GuessResultAdapter.GuessResultViewHolder> {

    private ArrayList<GuessResult> mGuessResult = new ArrayList<>();

    public void add(GuessResult guessResult) {
        if (guessResult != null) {
            this.mGuessResult.add(guessResult);
            this.notifyDataSetChanged();
        }
    }

    public void reset() {
        this.mGuessResult.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public GuessResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_guess_result, parent, false);
        return new GuessResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuessResultViewHolder holder, int position) {
        int idx = getItemCount() - position - 1;
        holder.bind(idx + 1, mGuessResult.get(idx));
    }

    @Override
    public int getItemCount() {
        return mGuessResult.size();
    }

    class GuessResultViewHolder extends RecyclerView.ViewHolder {

        private TextView tvIndex, tvNumber, tvBull, tvCow;

        GuessResultViewHolder(View itemView) {
            super(itemView);
            tvIndex = itemView.findViewById(R.id.tvIndex);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvBull = itemView.findViewById(R.id.tvBull);
            tvCow = itemView.findViewById(R.id.tvCow);
        }

        void bind(int index, GuessResult guessResult) {
            if (guessResult == null) return;
            tvIndex.setText(String.valueOf(index));
            tvNumber.setText(guessResult.getBullCowNumber().getNumberInString());
            tvBull.setText(String.valueOf(guessResult.getBull()));
            tvCow.setText(String.valueOf(guessResult.getCow()));
        }
    }
}
