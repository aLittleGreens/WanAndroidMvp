package com.littlegreens.baselibary.net.http;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/9/6 14:12
 */
public class SimpleCookieJar implements CookieJar {

	private final List<Cookie> allCookies = new ArrayList<>();

	@Override
	public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
		allCookies.addAll(cookies);
	}

	@Override
	public synchronized List<Cookie> loadForRequest(HttpUrl url) {
		List<Cookie> result = new ArrayList<>();
		for (Cookie cookie : allCookies) {
			if (cookie.matches(url)) {
				result.add(cookie);
			}
		}
		return result;
	}
}
