package oansweety.cpn.co.th.oanqrcode.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import oansweety.cpn.co.th.oanqrcode.R;

/**
 * Created by kachutima on 12/3/2561.
 */

public class MyAdapter extends BaseAdapter{

    private Context context;    // Create Pipe
    private String[] titleStrings, imageStrings;

    public MyAdapter(Context context,
                     String[] titleStrings,
                     String[] imageStrings) {
        this.context = context;
        this.titleStrings = titleStrings;
        this.imageStrings = imageStrings;
    }

    @Override
    public int getCount() {
        return titleStrings.length; // Count number of food
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  // LayoutInflater -> Virtual Layout
        View view1 = layoutInflater.inflate(R.layout.listview_layout, viewGroup, false);

        TextView textView = view1.findViewById(R.id.txtTitle);
        textView.setText(titleStrings[i]);

        ImageView imageView = view1.findViewById(R.id.imvImage);
        Picasso.get().load(imageStrings[i]).into(imageView);

        return view1;
    }
}
