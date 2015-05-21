package com.lancer.ecomm.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.lancer.ecomm.R;
import com.lancer.ecomm.domains.ProductCategory;
import com.lancer.ecomm.domains.ProductSubcategory;

public class ShopExpListAdapter extends BaseExpandableListAdapter {

	private Context mContext;
	private List<ProductCategory> mAllProducts;
	
	public ShopExpListAdapter(Context context, ArrayList<ProductCategory> allProducts) {
		mContext = context;
		mAllProducts = allProducts;
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mAllProducts.get(groupPosition).getSubcategories().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mAllProducts.get(groupPosition).getSubcategories().size();
	}

	static class SubcategoryViewHolder {
		TextView tvSubcategoryAllcategs;
	}
	
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		View childView = convertView;
		SubcategoryViewHolder holder;
		
		if(childView == null) {
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			childView = inflater.inflate(R.layout.child_subcategory_allcategs, parent, false);
			holder = new SubcategoryViewHolder();
			holder.tvSubcategoryAllcategs = (TextView)childView.findViewById(R.id.tv_subcategory_allcategs);
			childView.setTag(holder);
		} else {
			holder = (SubcategoryViewHolder)childView.getTag();
		}
		holder.tvSubcategoryAllcategs.setText(((ProductSubcategory)getChild(groupPosition, childPosition)).getName());
		
		return childView;
	} // getChildView

	@Override
	public Object getGroup(int groupPosition) {
		return mAllProducts.get(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public int getGroupCount() {
		return mAllProducts.size();
	}

	static class CategoryViewHolder {
		TextView tvCategoryAllcategs;
	}
	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		View childView = convertView;
		CategoryViewHolder holder;
		
		if(childView == null) {
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			childView = inflater.inflate(R.layout.child_category_allcategs, parent, false);
			holder = new CategoryViewHolder();
			holder.tvCategoryAllcategs = (TextView)childView.findViewById(R.id.tv_category_allcategs);
			childView.setTag(holder);
		} else {
			holder = (CategoryViewHolder)childView.getTag();
		}
		holder.tvCategoryAllcategs.setText(((ProductCategory)getGroup(groupPosition)).getName());
		return childView;
	}
	
//	@Override
//	public void onGroupExpanded(int groupPosition) {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public void onGroupCollapsed(int groupPosition) {
//		// TODO Auto-generated method stub
//	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

} // end ShopExpListAdapter