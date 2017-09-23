package cnic.cjh.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * https://leetcode.com/problems/encode-and-decode-tinyurl/description/
 * 
 * 535. Encode and Decode TinyURL
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
 * Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 * </pre>
 * 
 * @author caojunhui
 * @date Sep 23, 2017
 */
public class Problem_535
{
	private Map<String, String> long2short;
	private Map<String, String> short2long;

	public Problem_535()
	{
		long2short = new HashMap<String, String>();
		short2long = new HashMap<String, String>();
	}

	// Encodes a URL to a shortened URL.
	public String encode(String longUrl)
	{
		if(long2short.containsKey(longUrl))
			return (String)long2short.get(longUrl);
		String url = longUrl;
		url = url.replace("http://", "").replace("https://", "");
		url = url.substring(url.indexOf('/')+1,url.length());
		long random = Math.round(Math.random() * 10000000000L);
		String shortUrl = "http://tinyurl.com/"+Long.toString(random);
		long2short.put(longUrl, shortUrl);
		short2long.put(shortUrl, longUrl);
		return shortUrl;
	}

	// Decodes a shortened URL to its original URL.
	public String decode(String shortUrl)
	{
		return short2long.get(shortUrl);
	}
	public static void main(String[] args)
	{
		 Problem_535 codec = new Problem_535();
		 System.out.println(codec.decode(codec.encode("https://leetcode.com/problems/design-tinyurl")));
	}
}
