package com.betconstruct.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.betconstruct.demo.model.Child;
import com.betconstruct.demo.model.Group;
import com.betconstruct.demo.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

	private List<Item> itemsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		itemsList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Group group = new Group();
			group.setId(i);
			Log.d("Art", group.getId() + "");
			List<Child> children = new ArrayList<>();
			for (int j = 0; j < 2; j++) {
				Child child = new Child();
				child.setId(j);
				child.setGroupId(i);
				Log.d("Art", child.getGroupId() + "");
				children.add(child);
			}

			group.setChilds(children);
			group.setTitle(i + "");
			itemsList.add(group);

		}

		RecyclerView recyclerView = findViewById(R.id.r_v);
		recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		Adapter adapter = new Adapter(itemsList);
//		adapter.setChildCheckBoxListener(this);
//		adapter.setGroupCheckBoxListener(this);
		recyclerView.setAdapter(adapter);
	}

//	@Override
//	public void onChildItemCheckBoxClick(int groupId,boolean isChecked) {
//		Toast.makeText(this, "child check box" + " group Id:" + groupId, Toast.LENGTH_SHORT).show();
//
//		Group group= (Group) getGroupItem(groupId);
//		List<Child> childList=group.getChilds();
//		if(isChecked) {
//			int temp = 0;
//			for (int i = 0; i < childList.size(); i++) {
//				if (childList.get(i).isChecked()) {
//					temp++;
//				}
//			}
//			if (temp == childList.size()) {
//				group.setAllChecked(true);
//			}
//		}else {
//			group.setAllChecked(false);
//		}
//	}
//
//	public Item getGroupItem(int groupId) {
//		for (int i = 0; i < itemsList.size(); i++) {
//			if (itemsList.get(i).getId() == groupId) {
//				return itemsList.get(i);
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public void onGroupItemCheckBoxClick(int id,boolean isChecked) {
//		Toast.makeText(this, "group check box", Toast.LENGTH_SHORT).show();
//
//		Group group= (Group) getGroupItem(id);
//		List<Child> childList=group.getChilds();
//		if(isChecked) {
//			for (int i = 0; i < childList.size(); i++) {
//				if (!childList.get(i).isChecked()) {
//					childList.get(i).setChecked(true);
//				}
//			}
//		}else {
//			for (int i = 0; i < childList.size(); i++) {
//				if (childList.get(i).isChecked()) {
//					childList.get(i).setChecked(false);
//				}
//			}
//		}
//	}
}
