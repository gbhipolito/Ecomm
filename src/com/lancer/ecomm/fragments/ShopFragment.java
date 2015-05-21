package com.lancer.ecomm.fragments;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.lancer.ecomm.R;
import com.lancer.ecomm.adapters.ShopExpListAdapter;
import com.lancer.ecomm.domains.Product;
import com.lancer.ecomm.domains.ProductCategory;
import com.lancer.ecomm.domains.ProductSubcategory;
import com.lancer.ecomm.utils.ServerUtil;

public class ShopFragment extends Fragment {

	public static final String ALL_CATEGORIES_URL = "http://something";
	public static final String JSONTAG_NAME = "name";
	public static final String JSONTAG_PRICE = "price";
	public static final String JSONTAG_IMAGE = "image";
	public static final String JSONTAG_DESCRIPTION = "description";
	public static final String JSONTAG_URL = "url";
	
	public boolean isListCompleted = false;
	
	private final ArrayList<ProductCategory> mAllCategories = new ArrayList<ProductCategory>();
	private ExpandableListView elvAllCategories;
	private ShopExpListAdapter shopExpListAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		new ProductsDownloaderTask().execute();
	} // end onCreate

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_shop_categories, container, false);
		elvAllCategories = (ExpandableListView)view.findViewById(R.id.shopcategs_elv_allcategs);
		shopExpListAdapter = new ShopExpListAdapter(getActivity(), mAllCategories);
		elvAllCategories.setAdapter(shopExpListAdapter);
		return view;
	}
	
	private ArrayList<ProductCategory> fillCategories() throws IOException, JSONException {
		final String strCategories = ServerUtil.serverGet(ALL_CATEGORIES_URL);
		final JSONArray jsonarrCategories = new JSONArray(strCategories);
		final int len = jsonarrCategories.length();
		final ArrayList<ProductCategory> alCategories = new ArrayList<ProductCategory>();
		for(int i = 0; i < len; i++) {
			JSONObject obj = jsonarrCategories.getJSONObject(i);
			alCategories.add(new ProductCategory(obj.getString(JSONTAG_NAME), obj.getString(JSONTAG_URL), fillSubcategories(obj.getString(JSONTAG_URL))));
		}
		return alCategories;
	}
	
	private ArrayList<ProductSubcategory> fillSubcategories(String subcategoryUrl) throws IOException, JSONException {
		final String strSubcategories = ServerUtil.serverGet(subcategoryUrl);
		final JSONArray jsonarrSubcategories = new JSONArray(strSubcategories);
		final int len = jsonarrSubcategories.length();
		final ArrayList<ProductSubcategory> alSubcategories = new ArrayList<ProductSubcategory>();
		for(int i = 0; i < len; i++) {
			JSONObject obj = jsonarrSubcategories.getJSONObject(i);
			alSubcategories.add(new ProductSubcategory(obj.getString(JSONTAG_NAME), obj.getString(JSONTAG_URL), fillProducts(obj.getString(JSONTAG_URL))));
		}
		return alSubcategories;
	}
	
	private ArrayList<Product> fillProducts(String productUrl) throws IOException, JSONException {
		final String strProducts = ServerUtil.serverGet(productUrl);
		final JSONArray jsonarrProducts = new JSONArray(strProducts);
		final int len = jsonarrProducts.length();
		final ArrayList<Product> alProducts = new ArrayList<Product>();
		for(int i = 0; i < len; i++) {
			JSONObject obj = jsonarrProducts.getJSONObject(i);
			alProducts.add(new Product(obj.getString(JSONTAG_NAME), obj.getInt(JSONTAG_PRICE), obj.getString(JSONTAG_IMAGE), obj.getString(JSONTAG_DESCRIPTION)));
		}
		return alProducts;
	}
	
	private class ProductsDownloaderTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... unused) {
			try {
				mAllCategories.clear();
				mAllCategories.addAll(fillCategories());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		} // end doInBackground

		@Override
		protected void onPostExecute(Void result) {
			isListCompleted = true;
			if(shopExpListAdapter != null) {
				shopExpListAdapter.notifyDataSetChanged();
			}
		}
		
	} // end ProductsDownloaderTask

} // end ShopFragment