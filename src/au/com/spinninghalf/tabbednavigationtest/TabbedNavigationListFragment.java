package au.com.spinninghalf.tabbednavigationtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class TabbedNavigationListFragment extends SherlockListFragment {
	OnGigListSelectedListener mCallback;
	
	//the container activity must implement this interface in order for this
	//fragment to communicate to it user events/selections.
	public interface OnGigListSelectedListener {
		public void onGigSelected(int position);
	}
	
	
	//why arent we using SherlockActivity here? if i try it results in an error, namely mCallback is null.
	@Override
	public void onAttach(Activity activity) {
        super.onAttach(activity);

        //This makes sure that the container activity has implemented
        //the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnGigListSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
		
		
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the list fragment and add it as our sole content.
          setListAdapter(new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, Shakespeare.TITLES));
    }
	
	
	
	
	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i("FragmentList", "Item clicked: " + id);
        mCallback.onGigSelected(position);
    }
}
