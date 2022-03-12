package sv.com.epsilon.util;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {

	public QuickSort() {

	}

	public void Sort(List<Object> list) {
		int size = list.size();
		if (size > 1) {
			QuickSortable(list, size);
		} else {
			// BoobleSortable(list,size);
		}

	}

	private List<Object> QuickSortable(List<Object> list, int size) {
		
		if (size > 5) {
			int pivot = size / 2;
			ArrayList<Object> right = new ArrayList<Object>();
			ArrayList<Object> left = new ArrayList<Object>();

			for (int i = 0; i < size; i++) {
				Object item = list.get(i);
				int j = this.toComparable(item, list.get(pivot));
				if (j > 0) {
					left.add(item);
				} else {
					right.add(item);
				}
			}
			left.add(list.get(pivot));
			
			
			
			if (left.size() > 1)
				left = (ArrayList<Object>) QuickSortable(left, left.size());

			if (right.size() > 1)
				right = (ArrayList<Object>) QuickSortable(right, right.size());

			
			ArrayList<Object> all = left;
			all.addAll(0, left);
			all.addAll(all.size(), right);

			System.out.println("array generado {"+all+"}");
			return all;
		} else {

			return  BoobleSortable(list);
		}

	}

	private List<Object> BoobleSortable(List<Object> list) {
		
		
		Object tmp;
		Object actual;
		
		for(int i=0;i<5;i++){
			actual=list.get(i);
			for(int j=0;j<5;j++){
				if(this.toComparable(actual, list.get(j))>0){
					tmp=actual;
					list.add(i,list.get(j));
					list.add(j, tmp);
				}
			}
		}
		
		
		
		return new ArrayList<Object>();
		
	}

	public int toComparable(Object obj1, Object obj2) {
		//System.out.println(obj1 + " - " + obj2);
		return obj1.toString().compareTo(obj2.toString());
	}

}
