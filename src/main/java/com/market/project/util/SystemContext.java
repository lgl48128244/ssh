package com.market.project.util;

/** 
 * 使用threadLocal简化API 
 * 由于封装的分页类AbstractPageManager，中的方法参数offSet和pageSize必要但是每个方法都必须写 
 * 所以使用threadLocal简化 
 * @author lzq 
 * 
 */
public class SystemContext {
	//定义两个threadLocal变量：offSet和pageSize  
	private static ThreadLocal<Integer> pageOffset = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();

	public static int getPageOffset() {
		return pageOffset.get();
	}

	public static void setPageOffset(int _pageOffset) {
		pageOffset.set(_pageOffset);
	}

	public static int getPageSize() {
		return pageSize.get();
	}

	public static void setPageSize(int _pageSize) {
		pageSize.set(_pageSize);
	}

	public static void removePageOffset() {
		pageOffset.remove();
	}

	public static void removePageSize() {
		pageSize.remove();
	}
}
