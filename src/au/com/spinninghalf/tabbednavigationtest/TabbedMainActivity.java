package au.com.spinninghalf.tabbednavigationtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;

public class TabbedMainActivity extends SherlockFragmentActivity {
	private final static String TAG = "TabbedMainActivity";
	
	TabListenerMobile<TabbedNavigationListFragment> listTabListenerMobile;
	TabListenerMobile<TabbedNavigationGigFragment> gigTabListenerMobile;
	
	TabListenerTablet<TabbedNavigationListFragment, TabbedNavigationGigFragment> listTabListenerTablet;
	TabListenerTablet<TabbedNavigationGigFragment, TabbedNavigationListFragment> gigTabListenerTablet;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			
			View fragmentContainer = findViewById(R.id.TabbedMainFragmentContainer);
			
			ActionBar actionBar = getSupportActionBar();
			getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			
			//use tablet navigation if the list and gig fragments are both available
			boolean tabletLayout = fragmentContainer == null;
			
			if(!tabletLayout) {
				//using mobile layout version of main.xml
				//Create and add the list tab
			      Tab listTab = actionBar.newTab();

			      listTabListenerMobile = new TabListenerMobile<TabbedNavigationListFragment>
			        (this, R.id.TabbedMainFragmentContainer, TabbedNavigationListFragment.class);

			      listTab.setText("List")
			             .setContentDescription("List of Gigs")
			             .setTabListener(listTabListenerMobile);

			      actionBar.addTab(listTab);
			      
			      //Create and add the gig tab
			      Tab gigTab = actionBar.newTab();
			      
			      gigTabListenerMobile = new TabListenerMobile<TabbedNavigationGigFragment>
			         (this, R.id.TabbedMainFragmentContainer, TabbedNavigationGigFragment.class);
			      
			      gigTab.setText("Gig Tab")
			      		.setContentDescription("Gig View")
			      		.setTabListener(gigTabListenerMobile);
			      		
			      actionBar.addTab(gigTab);
				
				
				
			} else {
				//in tablet navigation version of main.xml
				//Create and add the list tab
			      Tab listTab = actionBar.newTab();

			      listTabListenerTablet = new TabListenerTablet<TabbedNavigationListFragment,TabbedNavigationGigFragment >
			        (this, R.id.TabbedTabletFragmentContainer1, R.id.TabbedTabletFragmentContainer2, TabbedNavigationListFragment.class, TabbedNavigationGigFragment.class);

			      listTab.setText("List")
			             .setContentDescription("List of Gigs")
			             .setTabListener(listTabListenerTablet);

			      actionBar.addTab(listTab);
			      
			      //Create and add the gig tab
			      Tab gigTab = actionBar.newTab();
			      
			      gigTabListenerTablet = new TabListenerTablet<TabbedNavigationGigFragment, TabbedNavigationListFragment>
			         (this, R.id.TabbedTabletFragmentContainer1, R.id.TabbedTabletFragmentContainer2, TabbedNavigationGigFragment.class, TabbedNavigationListFragment.class);
			      
			      gigTab.setText("Gig Tab")
			      		.setContentDescription("Gig View")
			      		.setTabListener(gigTabListenerTablet);
			      		
			      actionBar.addTab(gigTab);
				
				
				
			}
	}
	
	
	public static class TabListenerMobile<T extends SherlockFragment> implements ActionBar.TabListener {
		  
		    private SherlockFragment fragment;
		    private FragmentActivity activity;
		    private Class<T> fragmentClass;
		    private int fragmentContainer;
		  
		    public TabListenerMobile(FragmentActivity activity, int fragmentContainer, 
		                       Class<T> fragmentClass) {
		  
		      this.activity = activity;
		      this.fragmentContainer = fragmentContainer;
		      this.fragmentClass = fragmentClass;
		    }
		  
		    // Called when a new tab has been selected
		    public void onTabSelected(Tab tab, FragmentTransaction ft) {
		      if (fragment == null) {
		        String fragmentName = fragmentClass.getName();
		        fragment = (SherlockFragment) Fragment.instantiate(activity, fragmentName); 
		        ft.add(fragmentContainer, fragment, fragmentName);
		      } else
		        ft.attach(fragment);
		    }
		  
		    // Called on the currently selected tab when a different tag is
		    // selected. 
		    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		      if (fragment != null)
		        ft.detach(fragment);
		    } 
		  
		    // Called when the selected tab is selected.
		    public void onTabReselected(Tab tab, FragmentTransaction ft) {
		      if (fragment != null)
		        ft.attach(fragment);
		    }
	  }
	
	
	//tab listener for when in tablet mode and using _2_ fragments in the container.
	public static class TabListenerTablet<T extends SherlockFragment, U extends SherlockFragment> implements ActionBar.TabListener {
		  
		    private SherlockFragment fragment1;
		    private SherlockFragment fragment2;
		    private FragmentActivity activity;
		    private Class<T> fragmentClass1;
		    private Class<U> fragmentClass2;
		    private int fragmentContainer1;
		    private int fragmentContainer2;
		  
		    public TabListenerTablet(FragmentActivity activity, int fragmentContainer1, int fragmentContainer2, 
		                       Class<T> fragmentClass1, Class<U> fragmentClass2) {
		  
		          this.activity = activity;
		          this.fragmentContainer1 = fragmentContainer1;
		          this.fragmentContainer2 = fragmentContainer2;
		          this.fragmentClass1 = fragmentClass1;
		          this.fragmentClass2 = fragmentClass2;
		    }
		  
		    // Called when a new tab has been selected
		    public void onTabSelected(Tab tab, FragmentTransaction ft) {
		    	
		      //check if both fragments are null. If so, create them and add to container.
		      if (fragment1 == null && fragment2 == null) {
		          String fragmentName1 = fragmentClass1.getName();
		          fragment1 = (SherlockFragment) Fragment.instantiate(activity, fragmentName1); 
		          ft.add(fragmentContainer1, fragment1, fragmentName1);
		        
		          String fragmentName2 = fragmentClass2.getName();
		          fragment2 = (SherlockFragment) Fragment.instantiate(activity, fragmentName2); 
		          ft.add(fragmentContainer2, fragment2, fragmentName2);
		          
		          Log.i(TAG, "in TabListenerTablet:onTabSelected 1st if block");
		          Log.i(TAG, "in TabListenerTablet:onTabSelected 1st if block. fragmentContainer1 = " + fragmentContainer1);
		          Log.i(TAG, "in TabListenerTablet:onTabSelected 1st if block. fragmentContainer2 = " + fragmentContainer2);
		      } else if (fragment1 == null) {
		    	  
		    	  //fragment2 is already instantiated so dont recreate it
		    	  String fragmentName1 = fragmentClass1.getName();
			      fragment1 = (SherlockFragment) Fragment.instantiate(activity, fragmentName1); 
			      ft.add(fragmentContainer1, fragment1, fragmentName1);
			      
			      //ft.add(fragmentContainer, fragment2);
			      ft.attach(fragment2);
			      Log.i(TAG, "in TabListenerTablet:onTabSelected 2nd if block");
		      } else if (fragment2 == null) {
		    	  
		    	  //fragment1 is already instantiated so dont recreate it.
		    	  String fragmentName2 = fragmentClass2.getName();
			      fragment2 = (SherlockFragment) Fragment.instantiate(activity, fragmentName2); 
			      ft.add(fragmentContainer2, fragment2, fragmentName2);
			      
			      //ft.add(fragmentContainer, fragment1);
			      ft.attach(fragment1);
			      Log.i(TAG, "in TabListenerTablet:onTabSelected 3rd if block");
		      } else {
		    	  
		    	  //both fragments are already instantiated.
		          ft.attach(fragment1);
		          ft.attach(fragment2);
		          Log.i(TAG, "in TabListenerTablet:onTabSelected 4th if block");
		      }
		    }
		  
		    // Called on the currently selected tab when a different tag is
		    // selected. 
		    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			      if (fragment1 != null && fragment2 != null) {
			        ft.detach(fragment1);
			        ft.detach(fragment2);
			        Log.i(TAG, "in TabListenerTablet:onTabUnselected. In 1st if block");
			      } else if (fragment1 != null) {
			    	  ft.detach(fragment1);
			    	  Log.i(TAG, "in TabListenerTablet:onTabUnselected. In 2st if block");
			      } else if (fragment2 != null) {
			    	  ft.detach(fragment2);
			    	  Log.i(TAG, "in TabListenerTablet:onTabUnselected. In 3st if block");
			      }
		    } 
		  
		    // Called when the selected tab is selected.
		    public void onTabReselected(Tab tab, FragmentTransaction ft) {
			      if (fragment1 != null && fragment2 != null) {
			        ft.attach(fragment1);
			        ft.attach(fragment2);
			      } else if (fragment1 != null) {
			    	  ft.attach(fragment1);
			      } else if (fragment2 != null) {
			    	  ft.attach(fragment2);
			      }
		    }
    }
}
