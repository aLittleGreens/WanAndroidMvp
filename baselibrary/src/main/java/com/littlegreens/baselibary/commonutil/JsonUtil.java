package com.littlegreens.baselibary.commonutil;

import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonUtil {

	private static Gson gson;

	public static <T> T parseJStr2Object(Class<T> clazz, String jstr) {
		if (TextUtils.isEmpty(jstr)) {
			return null;
		}
		if (gson == null) {
			gson = new Gson();
		}
		T bean = null;
		try {
			bean = gson.fromJson(jstr, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	public static String parseObject2Str(Object object) {
		if (object == null) {
			return null;
		}
		if (gson == null) {
			gson = new Gson();
		}
		String str = gson.toJson(object);
		return str;
	}

	public static <T> List<T> fromJsonArray(String json, Class<T> clazz) {
		try {
			List<T> lst = new ArrayList();
			JsonArray array = (new JsonParser()).parse(json).getAsJsonArray();
			Iterator var4 = array.iterator();

			while(var4.hasNext()) {
				JsonElement elem = (JsonElement)var4.next();
				lst.add((new Gson()).fromJson(elem, clazz));
			}

			return lst;
		} catch (Exception var6) {
			Log.w("GsonHelper", "", var6);
			return null;
		}
	}
}
