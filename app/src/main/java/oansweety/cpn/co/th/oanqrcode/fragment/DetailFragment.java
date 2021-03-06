package oansweety.cpn.co.th.oanqrcode.fragment;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import oansweety.cpn.co.th.oanqrcode.R;

/**
 * Created by kachutima on 12/3/2561.
 */

public class DetailFragment extends Fragment{

    public static DetailFragment detailInstance(
            String nameFood,
            String imagePath,
            String category,
            String price,
            String detail) {

        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("NameFood", nameFood);
        bundle.putString("ImagePath", imagePath);
        bundle.putString("Category", category);
        bundle.putString("Price", price);
        bundle.putString("Detail", detail);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Show All
        showAll();

//        Back Controller
        backController();

    }   // Main Method

    private void backController() {

        Button button = getView().findViewById(R.id.btnShowListView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

    }

    private void showAll() {
        //        Title
        TextView titleTextView = getView().findViewById(R.id.txtTitle);
        String titleString = getArguments().getString("NameFood", "NotThing");  // If space value show "NotThing"
        titleTextView.setText(titleString);

//        Category
        TextView categoryTextView = getView().findViewById(R.id.txtCategory);
        categoryTextView.setText(getArguments().getString("Category"));

//        Price
        TextView priceTextView = getView().findViewById(R.id.txtPrice);
        priceTextView.setText(getArguments().getString("Price"));

//        Detail
        TextView detailTextView = getView().findViewById(R.id.txtDetail);
        detailTextView.setText(getArguments().getString("Detail"));

//        Image
        ImageView imageView = getView().findViewById(R.id.imvImage);
        Picasso.get().load(getArguments().getString("ImagePath", "http://androidthai.in.th/mua/lmage/food1.jpg"))
                .into(imageView);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }
}
