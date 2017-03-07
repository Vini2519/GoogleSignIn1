package com.example.vineetjain.googlesignin.Home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vineetjain.googlesignin.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hejunlin.superindicatorlibray.CircleIndicator;
import com.hejunlin.superindicatorlibray.LoopViewPager;
import com.squareup.picasso.Picasso;


public class Home_Fragment extends Fragment {
    private LoopViewPager viewPager;
    private CircleIndicator indicator;
    DatabaseReference databaseReference;
    FirebaseRecyclerAdapter<FirebaseModel, FirebaseViewHolder> firebaseRecyclerAdapter;
    RecyclerView recyclerView;

    public Home_Fragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home_, container, false);
        viewPager = (LoopViewPager) view.findViewById(R.id.viewpager);
        indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FirebaseModel, FirebaseViewHolder>(
                FirebaseModel.class,
                R.layout.notice_item_view,
                FirebaseViewHolder.class,
                databaseReference.child("notices").getRef()
        ) {
            @Override
            protected void populateViewHolder(final FirebaseViewHolder viewHolder, final FirebaseModel model, final int position) {
                viewHolder.title.setText(model.getTitle());
                viewHolder.file_url.setText(model.getFile());
                if (model.getImage_url().equals(""))
                    viewHolder.image.setVisibility(View.GONE);
                else
                    Picasso.with(getContext()).load(model.getImage_url()).into(viewHolder.image);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!model.getImage_url().equals("")) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(model.getImage_url()));
                            getContext().startActivity(intent);
                        } else {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(model.getFile()));
                            getContext().startActivity(intent);
                        }
                    }
                });
                System.out.println(model.getTitle());
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);

        firebaseRecyclerAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = firebaseRecyclerAdapter.getItemCount();
                int lastVisiblePosition =
                        linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    recyclerView.scrollToPosition(positionStart);
                }
            }
        });
        viewPager.setAdapter(new PicAdapter(Home_Fragment.this));
        viewPager.setLooperPic(true);
        indicator.setViewPager(viewPager);
        return view;
    }
}
