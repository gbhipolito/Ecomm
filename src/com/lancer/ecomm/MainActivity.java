package com.lancer.ecomm;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.lancer.ecomm.fragments.HomeFragment;
import com.lancer.ecomm.fragments.ShopFragment;
import com.lancer.ecomm.listeners.NavigationOnClickListener;

public class MainActivity extends Activity {

	public static final short FRAG_MODE_HOME = 1;
	public static final short FRAG_MODE_SHOP = 2;
	public static final short FRAG_MODE_CART = 3;
	public static final short FRAG_MODE_TESTIMONIALS = 4;
	public static final short FRAG_MODE_MORE = 5;
	
	private final HomeFragment homeFragment = new HomeFragment();
	private final ShopFragment shopFragment = new ShopFragment();
	
	private Button btnHome;
	private Button btnShop;
	private Button btnCart;
	private Button btnTestimonials;
	private Button btnMore;
	private FragmentManager fm;
	private short currentFragmentMode = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
		
		btnHome = (Button)findViewById(R.id.nav_home);
		btnShop = (Button)findViewById(R.id.nav_shop);
		btnCart = (Button)findViewById(R.id.nav_cart);
		btnTestimonials = (Button)findViewById(R.id.nav_testimonials);
		btnMore = (Button)findViewById(R.id.nav_more);
		fm = getFragmentManager();
		final NavigationOnClickListener navigationOnClickListener = new NavigationOnClickListener(this);
		
		btnHome.setOnClickListener(navigationOnClickListener);
		btnShop.setOnClickListener(navigationOnClickListener);
		btnCart.setOnClickListener(navigationOnClickListener);
		btnTestimonials.setOnClickListener(navigationOnClickListener);
		btnMore.setOnClickListener(navigationOnClickListener);
		
		switchToHome();
	} // end onCreate

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public synchronized void switchToHome() {
		if(currentFragmentMode != FRAG_MODE_HOME) {
			fm.beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
			desselectNav();
			btnHome.setSelected(true);
			currentFragmentMode = FRAG_MODE_HOME;
		}
	}
	
	public synchronized void switchToShop() {
		if(currentFragmentMode != FRAG_MODE_SHOP) {
			fm.beginTransaction().replace(R.id.fragment_container, shopFragment).commit();
			desselectNav();
			btnHome.setSelected(true);
			currentFragmentMode = FRAG_MODE_SHOP;
		}
	}
	
	/**
	 * desselect all navigation buttons
	 */
	private void desselectNav() {
		btnHome.setSelected(false);
		btnShop.setSelected(false);
		btnCart.setSelected(false);
		btnTestimonials.setSelected(false);
		btnMore.setSelected(false);
	}
} // end MainActivity