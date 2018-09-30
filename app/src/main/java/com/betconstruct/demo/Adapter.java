package com.betconstruct.demo;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.betconstruct.demo.model.Child;
import com.betconstruct.demo.model.Group;
import com.betconstruct.demo.model.Item;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.betconstruct.demo.Adapter.Type.CHILD;
import static com.betconstruct.demo.Adapter.Type.GROUP;

public class Adapter extends RecyclerView.Adapter<Adapter.BaseHolder> {
	//	private ChildCheckBoxListener childCheckBoxListener;
	//	private GroupCheckBoxListener groupCheckBoxListener;
	private List<Item> itemsList;
	private Set<Integer> checkedGroupItemsSet = new HashSet<>();
	private Map<Integer, Set<Integer>> itemsMap;
	private Map<Integer, Group> groupItemsMap;

	@IntDef({GROUP, CHILD})
	@Retention(RetentionPolicy.SOURCE)
	public @interface Type {
		int GROUP = R.layout.layout_group;
		int CHILD = R.layout.layout_child;
	}

	//im karciqov avel en
//	public static HashSet<Integer> mChildChecker = new HashSet<>();
//	private int groupCount;
//	private int childCount;

	public Adapter(List<Item> itemsList) {
		this.itemsList = new ArrayList<>();
		this.itemsMap = new HashMap<>();
		this.groupItemsMap = new HashMap<>();
		for (Item item : itemsList) {
			if (!item.isGroup()) {
				this.itemsList.add(item);//palyubomu stex chi mtnum, vortev sax group-en galis ????
				itemsMap.put(item.getId(), null); //kareliya qnnarkel u hanel
			} else {
				this.itemsList.add(item);
				List<Child> childItemsList = ((Group) item).getChilds();
				this.itemsList.addAll(childItemsList);

				itemsMap.put(item.getId(), new HashSet<>());
				groupItemsMap.put(item.getId(), (Group) item);
			}
		}
		Log.d("DefaultMap", this.itemsList.size() + "");
		Log.d("DefaultMap", itemsMap.keySet().toString());
		Log.d("DefaultMap",groupItemsMap.keySet().toString());
	}

//	public Adapter(List<Item> itemsList) {
//		this.itemsMap = new HashMap<>();
//		this.itemsList=new ArrayList<>(itemsList);
//		Log.d("DefaultMap", itemsList.size()+"");
//		for (Item item : itemsList) {
//			if (item.isGroup()) {
//				itemsMap.put(item.getId(), ((Group) item).getChilds());
//				Log.d("DefaultMap", itemsMap.keySet().toString());
//			} else {
//				itemsMap.put(item.getId(), null);//?? kareliya qnnarkel u hanel
//			}
//		}
//	}


	@NonNull
	@Override
	public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext())
				.inflate(viewType, parent, false);
		return viewType == CHILD ? new ChildHolder(view) : new GroupHolder(view);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
		//      holder.onBind(itemsList.get(position));

		final Item item = itemsList.get(position);
		if (item.isGroup()) {
			if (checkedGroupItemsSet.contains(item.getId())) {
				((GroupHolder) holder).checkBox.setChecked(true);
			} else {
				((GroupHolder) holder).checkBox.setChecked(false);
			}
		} else {
			Set<Integer> checkedChildItemsSet = itemsMap.get(item.getGroupId());
			if (checkedChildItemsSet.contains(item.getId())) {
				((ChildHolder) holder).checkBox.setChecked(true);
			} else {
				((ChildHolder) holder).checkBox.setChecked(false);
			}
		}

	}

	@Override
	public int getItemCount() {
		return itemsList.size();
	}

	@Override
	public int getItemViewType(int position) {

		return itemsList.get(position).isGroup() ? GROUP : CHILD;
	}


	public abstract class BaseHolder<I> extends RecyclerView.ViewHolder {

		private BaseHolder(View itemView) {
			super(itemView);
		}

		//	public abstract void onBind(I item);
	}

	public class GroupHolder extends BaseHolder<Group> {

		private CheckBox checkBox;

		private TextView title;

		public GroupHolder(View itemView) {
			super(itemView);

			checkBox = itemView.findViewById(R.id.region_checkbox);
			title = itemView.findViewById(R.id.title_region);

			checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					int pos = getAdapterPosition();
					Group item = (Group) itemsList.get(pos);
					List<Child> childList = item.getChilds();
					if (isChecked) {
						checkedGroupItemsSet.add(item.getId());
						//item.setAllChecked(true);

						for (int i = 0; i < childList.size(); i++) {
							Child child = childList.get(i);
							Set<Integer> checkedChildItemsSet = itemsMap.get(child.getGroupId());
							if (!(checkedChildItemsSet.contains(child.getId()))) {
								checkedChildItemsSet.add(child.getId());
							}
						}
					} else {
						checkedGroupItemsSet.remove(item.getId());
						//item.setAllChecked(false);

						for (int i = 0; i < childList.size(); i++) {
							Child child = childList.get(i);
							Set<Integer> checkedChildItemsSet = itemsMap.get(child.getGroupId());
							if (checkedChildItemsSet.contains(child.getId())) {
								checkedChildItemsSet.remove(child.getId());
							}
						}
					}
					notifyItemRangeChanged(pos + 1, childList.size());
					//notifyDataSetChanged();
				}
			});

//		@Override
//		public void onBind(final Group group) {
//
//			checkBox.setChecked(group.isAllChecked());
//
//
//			checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//				@Override
//				public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//					group.setAllChecked(b);
//
////                    int count = 0;
//					for (int i = 0; i < getGroupItemCount(group); i++) {
//						if (group.isAllChecked()) {
//							groupCount++;
//						} else {
//							if (groupCount > 0) {
//								groupCount--;
//							}
//						}
//					}
//
//					if (groupCount == getGroupItemCount(group)) {
//						System.out.println("COUNT:   " + groupCount);
//						changeParentState(true);
//
//					} else {
////                        changeParentState(false);
//					}
//
////                    checkedFromMatchesItem(b, position);
//				}
//			});
//
//
//		}
		}
	}


	public class ChildHolder extends BaseHolder<Child> {
		CheckBox checkBox;
		TextView matchDate;
		TextView teamName1;
		TextView teamName2;

		public ChildHolder(View itemView) {
			super(itemView);

			checkBox = itemView.findViewById(R.id.competition_child_checkbox);
			matchDate = itemView.findViewById(R.id.match_date);
			teamName1 = itemView.findViewById(R.id.team_1_name);
			teamName2 = itemView.findViewById(R.id.team_2_name);

			checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					int pos = getAdapterPosition();
					Child item = (Child) itemsList.get(pos);
					Set<Integer> checkedChildItemsSet = itemsMap.get(item.getGroupId());
					Group group = groupItemsMap.get(item.getGroupId());
					List<Child> childItemsList = group.getChilds();

					if (isChecked) {
						checkedChildItemsSet.add(item.getId());
						//item.setChecked(true);

						if (childItemsList.size() == checkedChildItemsSet.size()) {
							group.setAllChecked(true);
							checkedGroupItemsSet.add(group.getId());
						}
					} else {
						checkedChildItemsSet.remove(item.getId());
						//item.setChecked(false);

						group.setAllChecked(false);
						checkedGroupItemsSet.remove(group.getId());
					}
					//notifyDataSetChanged();
					//notifyItemRangeChanged(pos + 1, childList.size());
				}
			});
//		@Override
//		public void onBind(final Child child) {
//
//
//			matchDate.setText(" This is test message ");
//
//			final Child currentMatch = child;
//
//			//TODO
//			final int currentMatchId = currentMatch.getMatchId();
//			teamName1.setText(currentMatch.getHomeTeamName());
//			teamName2.setText(currentMatch.getAwayTeamName());
//			matchDate.setText(currentMatch.getMatchDate());
//
//
//			if (currentMatch.isChecked()) {
//				mChildChecker.add(currentMatchId);
//				checkBox.setChecked(true);
//			} else {
//				mChildChecker.remove(currentMatchId);
//				checkBox.setChecked(false);
//			}
//
//			checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//
//				@Override
//				public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//					currentMatch.setChecked(b);
//					if (b) {
//						mChildChecker.add(currentMatchId);
//						childCount++;
//					} else {
//						mChildChecker.remove(currentMatchId);
//						if (childCount > 0) {
//							childCount--;
//						}
//					}
//
//					System.out.println("Count: " + childCount);
//
//					if (childCount == getChildItemCount(child)) {
//						System.out.println("COUNT:   child " + childCount);
//						childCount = 0;
////                        changeParentState(true, currentMatch);
//					} else {
////                        changeParentState(false, currentMatch);
//					}
////                    checkedFromMatchedItem(b, position);
//
//				}
//			});
//
//		}
		}
	}
//addonCheck

	private void changeParentState(boolean b) {

	}

	private int getGroupItemCount(Group group) {
		int count = 0;
		for (int i = 0; i < itemsList.size(); i++) {
			if (itemsList.get(i).isGroup()) {
				count += 1;
			}
		}
		return count;
	}

	private int getChildItemCount(Child child) {
		int count = 0;
		for (int i = 1; i < itemsList.size(); i++) {
			if (!itemsList.get(i).isGroup() && ((Child) itemsList.get(i)).getMatchId() == child.getMatchId()) {
				count += 1;
			} else {
				return count;
			}
		}
		return count;
	}

//	public interface ChildCheckBoxListener {
//		void onChildItemCheckBoxClick(int groupId, boolean isChecked);
//	}
//
//	public interface GroupCheckBoxListener {
//		void onGroupItemCheckBoxClick(int id, boolean isChecked);
//	}
//
//	public void setChildCheckBoxListener(ChildCheckBoxListener childCheckBoxListener) {
//		this.childCheckBoxListener = childCheckBoxListener;
//	}
//
//	public void setGroupCheckBoxListener(GroupCheckBoxListener groupCheckBoxListener) {
//		this.groupCheckBoxListener = groupCheckBoxListener;
}


