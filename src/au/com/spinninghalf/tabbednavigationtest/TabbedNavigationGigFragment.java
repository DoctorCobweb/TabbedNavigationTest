package au.com.spinninghalf.tabbednavigationtest;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class TabbedNavigationGigFragment extends SherlockFragment {
	private static final String TAG = "TabbedNavigationGigFragment";
	private int position;
	
	
	public TabbedNavigationGigFragment() {
		super();
	}
	
	public TabbedNavigationGigFragment(int position) {
		this.position = position;
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.gig_fragment, container, false);
		TextView gigTextView = (TextView) getActivity().findViewById(R.id.gigFragmentTextView);
		Log.i(TAG, "in onCreateView and gigTextView = " + gigTextView);
		if (gigTextView != null) {
			gigTextView.setText(Shakespeare.DIALOGUE[this.position]);
		}
		return view;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		TextView gigTextView = (TextView) getActivity().findViewById(R.id.gigFragmentTextView);
		Log.i(TAG, "in onStart and gigTextView = " + gigTextView);
		if (gigTextView != null) {
			gigTextView.setText(Shakespeare.DIALOGUE[this.position]);
		}
	}
	
	public void updateGigTextView(int position) {
		TextView gigTextView = (TextView) getActivity().findViewById(R.id.gigFragmentTextView);
		gigTextView.setText(Shakespeare.DIALOGUE[position]);
		//gigTextView.setText("Hello");
	}
}
