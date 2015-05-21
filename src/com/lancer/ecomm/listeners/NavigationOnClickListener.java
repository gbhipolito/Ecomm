package com.lancer.ecomm.listeners;

import com.lancer.ecomm.MainActivity;
import com.lancer.ecomm.R;

import android.view.View;
import android.view.View.OnClickListener;

public class NavigationOnClickListener implements OnClickListener {

	private final MainActivity mMainActivity;
	
	public NavigationOnClickListener(MainActivity mainActivity) {
		mMainActivity = mainActivity;
	}
	
	@Override
	public void onClick(View view) {
		int btnId = view.getId();
		
		switch(btnId) {
			case R.id.nav_home:
				mMainActivity.switchToHome();
				break;
				
			case R.id.nav_shop:
				mMainActivity.switchToShop();
				break;
				
			case R.id.nav_cart:
				break;
				
			case R.id.nav_testimonials:
				break;
				
			case R.id.nav_more:
				break;
		} // switch(btnId)
	} // end onClick
} // end NavigationOnClickListener