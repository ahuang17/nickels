package com.cta;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CtaController {

    public ArrayList<String> custWallet = new ArrayList<String>();

    public ArrayList<Integer> custWalletInt = new ArrayList<Integer>();

	@GetMapping("/checkFare/{fare}")
	public String getNumFare(@PathVariable int fare) {
        return fareWaysResults(custWallet, fare);
	}

    @GetMapping("/checkFareWays/{fare}")
    public int getNumFareWays(@PathVariable int fare) {
        return fareWays(custWalletInt, fare);
	}

    @GetMapping("/addMoney/{money}")
    public void addMoney(@PathVariable String money) {
        String[] parts = money.split(",");
        for (int i = 0; i < parts.length; i++) {
            custWallet.add(parts[i]);
            custWalletInt.add(Integer.parseInt(parts[i]));
        }
    }

    @GetMapping("/checkWallet")
	public String getCustWallet() throws Exception{
        try{
            return custWallet.stream().map(Object::toString).collect(Collectors.joining(","));
        } catch (Exception e) {
            return "Wallet Empty";
        }
	}

    @GetMapping("/emptyWallet")
	public void emptyWallet() throws Exception{
        custWallet.clear();
	}

	public int fareWays(ArrayList<Integer> wallet, int sum) {

        HashMap formattedWallet = formatWalletWays(wallet);

        int[][] map = new int[wallet.size() + 1][sum + 1];

        map[0][0] = 1;
        for (int j = 1; j <= wallet.size(); j++) {
            for (int i = 0; i <= sum; i++) {
                map[j][i] += map[j - 1][i];
            }

            for (int k = 1; k <= (int) formattedWallet.get(wallet.get(j-1)); k++) {
                int initial = wallet.get(j - 1) * k;
                for (int i = initial; i <= sum; i++) {
                    map[j][i] += map[j - 1][i - initial];
                }
            }
        }
        return map[wallet.size()][sum];
    }

    public String fareWaysResults(ArrayList<String> wallet, int sum) {

        HashMap formattedWallet = formatWallet(wallet);

        ArrayList<String>[][] map = new ArrayList[wallet.size() + 1][sum + 1];

        ArrayList<String> init = new ArrayList<String>();
        init.add("");
        map[0][0] = init;
        for (int j = 1; j <= wallet.size(); j++) {
            for (int i = 0; i <= sum; i++) {
                //map[j][i] += map[j-1][i];
                if(map[j][i] != null && map[j-1][i] != null)
                    map[j][i] = addForAll(map[j][i], map[j-1][i]);
            }

            for (int k = 1; k <= (int) formattedWallet.get(wallet.get(j-1)); k++) {
                int initial = Integer.parseInt(wallet.get(j - 1)) * k;
                for (int i = initial; i <= sum; i++) {
                    //map[j][i] += map[j-1][i-initial];
                    if(map[j][i] != null && map[j-1][i-initial] != null)
                        map[j][i] = addForAll(map[j][i], map[j-1][i-initial]);
                }
            }
        }
        for(int i = sum; i > 0; i--) {
            for(int j = wallet.size(); j > 0; j--) {
                if(map[j][i] != null)
                    return map[j][i].get(0);
            }
        }
        return "No possible way to pay fare";
    }

    public ArrayList<String> addForAll(ArrayList<String> orig, ArrayList<String> modi) {
        ArrayList<String> res = new ArrayList<String>();
        for(int i = 0; i < orig.size(); i++) {
            for(int j = 0; j < modi.size(); j++) {
                res.add(orig.get(i) + modi.get(j));
            }
        }
        return res;
    }


    public HashMap<String, Integer> formatWallet(ArrayList<String> wallet) {
        Collections.sort(wallet);
        HashMap<String, Integer> counted = new HashMap<String, Integer>();
        for (String coin : wallet) {
            if (counted.containsKey(coin)) {
                counted.put(coin, counted.get(coin) + 1);
            } else {
                counted.put(coin, 1);
            }
        }
        return counted;
    }

    public HashMap<Integer, Integer> formatWalletWays(ArrayList<Integer> wallet) {
        Collections.sort(wallet);
        HashMap<Integer, Integer> counted = new HashMap<Integer, Integer>();
        for (Integer coin : wallet) {
            if (counted.containsKey(coin)) {
                counted.put(coin, counted.get(coin) + 1);
            } else {
                counted.put(coin, 1);
            }
        }
        return counted;
    }
}
