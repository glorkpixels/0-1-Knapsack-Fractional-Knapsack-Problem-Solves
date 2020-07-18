import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class _2400000145_Mehmet_Hamdi_Uslu {
	public static List<String> garageCost = new ArrayList<String>();
	public static List<investment> investmentCost = new ArrayList<investment>();
	public static List<String> customerDemands = new ArrayList<String>();

	public static int DP(int x, int p, int d, int b, int t, int c) {
//		 x  //month
//		 p	//produce rate
//		 d // intern fee
//		 B  // cost of car
//		 t  //tax rate
//		 c  //number of investment companies
		int month = x;
		int prate = p;
		int fare = d;
		int carcost = b;
		int taxrate = t;
		int comcount = c;
		List<String> DPGarageCost = new ArrayList<String>();
		List<investment> DPInvestment = new ArrayList<investment>();
		List<String> DPDemand = new ArrayList<String>();

		

		for (int i = 0; i < month; i++) {
			DPGarageCost.add(garageCost.get(i));
		}
		for (int i = 0; i < month; i++) {
			DPInvestment.add(investmentCost.get(i));
		}
		for (int i = 0; i < month; i++) {
			DPDemand.add(customerDemands.get(i));
		}

		List<Integer> cho = new ArrayList<Integer>();
		List<Integer> best = new ArrayList<Integer>();
		int invnow = -1;
		int bestthatmonth = 0;
		int monthcount = 0;
		boolean isitChanged = false;
		
		for (int i = 0; i < DPInvestment.size(); i++) {
			List<Integer> invcho = new ArrayList<Integer>();

			ArrayList<Integer> choiselist = new ArrayList<Integer>();
			investment inv = DPInvestment.get(i);

			int comp1 = inv.getC1();
			int comp2 = inv.getC2();
			int comp3 = inv.getC3();
			int comp4 = inv.getC4();
			int comp5 = inv.getC5();
			int comp6 = inv.getC6();

			if (comcount == 6) {
				invcho.add(comp1);
				invcho.add(comp2);
				invcho.add(comp3);
				invcho.add(comp4);
				invcho.add(comp5);
				invcho.add(comp6);
				bestthatmonth = inv.getBestvalthatmonth();
			}
			if (comcount == 5) {
				investment ins = new investment(comp1, comp2, comp3, comp4, comp5);

				bestthatmonth = ins.getBestvalthatmonth();
				invcho.add(comp1);
				invcho.add(comp2);
				invcho.add(comp3);
				invcho.add(comp4);
				invcho.add(comp5);
			}
			if (comcount == 4) {
				investment ins = new investment(comp1, comp2, comp3, comp4);

				bestthatmonth = ins.getBestvalthatmonth();
				invcho.add(comp1);
				invcho.add(comp2);
				invcho.add(comp3);
				invcho.add(comp4);
			}

			if (comcount == 3) {
				investment ins = new investment(comp1, comp2, comp3);

				bestthatmonth = ins.getBestvalthatmonth();
				invcho.add(comp1);
				invcho.add(comp2);
				invcho.add(comp3);
			}

			if (comcount == 2) {
				investment ins = new investment(comp1, comp2);
				bestthatmonth = ins.getBestvalthatmonth();
				invcho.add(comp1);
				invcho.add(comp2);
			}
//	System.out.println(invcho);
			int howmany = 0;
			for (int j = 0; j < invcho.size(); j++) {
				if (monthcount == 0) {
					if (bestthatmonth == invcho.get(j)) {
						howmany++;
						cho.add(j);
					}
				} else {

					if (bestthatmonth == invcho.get(j)) {
						howmany++;
						choiselist.add(j);
					}
				}

			}

//			System.out.println(choiselist);
			if (monthcount > 0) {
				int g = cho.get(monthcount - 1);
				if (choiselist.contains(g)) {
					cho.add(g);
					
				} else {
					cho.add(choiselist.get(0));
				}

			}
//			System.out.println(cho);
			best.add(bestthatmonth);
			howmany = 0;
			monthcount++;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		System.out.println(best);
		int carGar=0;
		int totaldemand =0;
		int mainMoney = 0;
		int nmMoneyadd = 0;
		int internfees = 0;
		monthcount=0;

		int PROFIT = 0;
		int COST = 0;
//		System.out.println(cho);
		for (int i = 0; i < DPDemand.size(); i++) {
			int demand = Integer.valueOf(DPDemand.get(i));
			totaldemand +=demand;
		}
		
		int tempMoney =0;
		for (int i = 0; i < DPDemand.size(); i++) {
			int demand = Integer.valueOf(DPDemand.get(i));
			
			int half = (demand * carcost) /2;
	
			
			if (demand > prate) {
				
			}
			
			
			
//			
//			int garcost =carGar * Integer.valueOf(garageCost.get(carGar));
//			mainMoney -=garcost;
//			COST+=garcost;
			internfees=0;
			PROFIT += half*2;
			
			
			
			if (monthcount !=0) {
				if (cho.get(monthcount) != cho.get(monthcount-1)) {
//					System.out.println("chng");
//					int taxreduce =(taxrate* mainMoney) /100;
//					mainMoney -=taxreduce;
//					System.out.println(taxreduce);
					mainMoney += half;
					mainMoney += nmMoneyadd;
					
					mainMoney += (best.get(monthcount) * mainMoney) /100;
					PROFIT += (best.get(monthcount) * mainMoney) /100;
				}
				else {
					mainMoney += half;
					mainMoney += nmMoneyadd;
					mainMoney += (best.get(monthcount) * mainMoney) /100;
					PROFIT += (best.get(monthcount) * mainMoney) /100;
				}
			
			}
			
			if (monthcount ==0) {
				mainMoney += half;
				mainMoney += nmMoneyadd;
				mainMoney += (best.get(monthcount) * mainMoney) /100;
				
			}
			if (monthcount == month-1) {
//				System.out.println(monthcount);
				mainMoney += nmMoneyadd;
			}
			nmMoneyadd = half;
			monthcount++;
			
		}
//		mainMoney-= (totaldemand - (prate * month))*fare*month*2;
//System.out.println(PROFIT);
		return mainMoney;

	}
	public static int Greedy(int x, int p, int d, int b, int t, int c) {
//		 x  //month
//		 p	//produce rate
//		 d // intern fee
//		 B  // cost of car
//		 t  //tax rate
//		 c  //number of investment companies
		int PROFIT = 0;
		int COST = 0;

		int month = x;
		int prate = p;
		int fare = d;
		int carcost = b;
		int taxrate = t;
		int comcount = c;


		List<String> GreedyGarageCost = new ArrayList<String>();
		List<investment> GreedyInvestment = new ArrayList<investment>();
		List<String> GreedyDemand = new ArrayList<String>();

		

		int mainMoney = 0;
		int nmMoneyadd = 0;
		int internfees = 0;

		int carGar = 0;

		int invnow = -1;

		int monthcount = 0;
		for (int i = 0; i < month; i++) {
			GreedyGarageCost.add(garageCost.get(i));
		}
		for (int i = 0; i < month; i++) {
			GreedyInvestment.add(investmentCost.get(i));
		}
		for (int i = 0; i < month; i++) {
			GreedyDemand.add(customerDemands.get(i));
		}

		for (int i = 0; i < GreedyDemand.size(); i++) {

			int demand = Integer.valueOf(GreedyDemand.get(i));
			int demander = Integer.valueOf(GreedyDemand.get(i));
			int itp;

			if (demand > prate) {
				if (carGar > 0) {
					demand = demand - carGar;
				}

				if (demand > prate) {
					itp = demand - prate; // internprod need
					if (itp > 0) {
						internfees = itp * fare;
					}
				}

			} else {

				itp = prate - demander;

				if (carGar > itp) {
					carGar -= itp;
				}

			}

			int half = (demander * carcost) / 2;
			if (monthcount == 0) {
				mainMoney += half;
				PROFIT += half;
			}
			if (mainMoney > 0) {

				investment inv = GreedyInvestment.get(monthcount);
				List<Integer> invcho = new ArrayList<Integer>();
				List<Integer> choiselist = new ArrayList<Integer>();

				int comp1 = inv.getC1();
				int comp2 = inv.getC2();
				int comp3 = inv.getC3();
				int comp4 = inv.getC4();
				int comp5 = inv.getC5();
				int comp6 = inv.getC6();
				int bestthatmonth = 0;

				if (comcount == 6) {
					invcho.add(comp1);
					invcho.add(comp2);
					invcho.add(comp3);
					invcho.add(comp4);
					invcho.add(comp5);
					invcho.add(comp6);
					bestthatmonth = inv.getBestvalthatmonth();
				}
				if (comcount == 5) {
					investment ins = new investment(comp1, comp2, comp3, comp4, comp5);

					bestthatmonth = ins.getBestvalthatmonth();
					invcho.add(comp1);
					invcho.add(comp2);
					invcho.add(comp3);
					invcho.add(comp4);
					invcho.add(comp5);
				}
				if (comcount == 4) {
					investment ins = new investment(comp1, comp2, comp3, comp4);

					bestthatmonth = ins.getBestvalthatmonth();
					invcho.add(comp1);
					invcho.add(comp2);
					invcho.add(comp3);
					invcho.add(comp4);
				}

				if (comcount == 3) {
					investment ins = new investment(comp1, comp2, comp3);

					bestthatmonth = ins.getBestvalthatmonth();
					invcho.add(comp1);
					invcho.add(comp2);
					invcho.add(comp3);
				}

				if (comcount == 2) {
					investment ins = new investment(comp1, comp2);

					bestthatmonth = ins.getBestvalthatmonth();
					invcho.add(comp1);
					invcho.add(comp2);
				}

				int tempmoney = mainMoney;

				for (int j = 0; j < invcho.size(); j++) {
					int lol = invcho.get(j);
					if (bestthatmonth == lol) {
						choiselist.add(j);
					}

				}
				boolean isitChanged = false;

				if (monthcount != 0) {
					for (int j = 0; j < choiselist.size(); j++) {
						if (invnow == choiselist.get(j)) {
							isitChanged = false;
						} else {
							isitChanged = true;

						}
					}
				}

				if (month != 0) {

					if (isitChanged == true) {
						invnow = choiselist.get(0);
						int rate = invcho.get(invnow);
						
						tempmoney = mainMoney - ((mainMoney * taxrate) / 100);
						tempmoney = (tempmoney * rate) / 100;

						mainMoney += tempmoney;
						PROFIT += tempmoney;
					} else {
						invnow = choiselist.get(0);

						int rate = invcho.get(invnow);
						tempmoney = (mainMoney * rate) / 100;

						mainMoney += tempmoney;
						PROFIT += tempmoney;
					}
				}

				else {
					int rate = bestthatmonth;
					tempmoney = (mainMoney * rate) / 100;

					mainMoney += tempmoney;
					PROFIT += tempmoney;
				}
//				System.out.println(choiselist);
			}

			if (month != 0) {
				mainMoney += half;
				mainMoney += nmMoneyadd;
				PROFIT += half;
			}

			if (carGar > -1) {

				mainMoney -= carGar * Integer.valueOf(garageCost.get(carGar));
				COST += carGar * Integer.valueOf(garageCost.get(carGar));
			}

			nmMoneyadd = 0;
			nmMoneyadd = half;
			PROFIT += half;
			mainMoney -= internfees;
			COST += internfees;

//			System.out.println(monthcount);
			monthcount++;
//			if (month == monthcount + 1) {
//				mainMoney += nmMoneyadd;
//				mainMoney -= internfees;
//			}
			internfees = 0;
		}

		return mainMoney;
	}

	public static void readInvestment(String filename) {

		try {
			File myObj = new File(filename);
			Scanner myReader = new Scanner(myObj);
			myReader.nextLine();
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] temp = data.split("	");

				int len = temp.length;
				if (len == 7) {
					investment ins = new investment(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),
							Integer.parseInt(temp[3]), Integer.parseInt(temp[4]), Integer.parseInt(temp[5]),
							Integer.parseInt(temp[6]));

					investmentCost.add(ins);
				}
				if (len == 6) {
					investment ins = new investment(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),
							Integer.parseInt(temp[3]), Integer.parseInt(temp[4]), Integer.parseInt(temp[5]));

					investmentCost.add(ins);
				}
				if (len == 5) {
					investment ins = new investment(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),
							Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));

					investmentCost.add(ins);
				}
				if (len == 4) {
					investment ins = new investment(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),
							Integer.parseInt(temp[3]));

					investmentCost.add(ins);
				}
				if (len == 3) {
					investment ins = new investment(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));

					investmentCost.add(ins);
				}

			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static void readDemand(String filename) {

		try {
			File myObj = new File(filename);
			Scanner myReader = new Scanner(myObj);
			myReader.nextLine();
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] temp = data.split("	");
				customerDemands.add(temp[1]);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static void readGarageCost(String filename) {

		try {
			File myObj = new File(filename);
			Scanner myReader = new Scanner(myObj);
			myReader.nextLine();
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] temp = data.split("	");
				garageCost.add(temp[1]);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String garageText = "garage_cost.txt";
		String investmentText = "investment.txt";
		String demandText = "month_demand.txt";

		readDemand(demandText);
		readGarageCost(garageText);
		readInvestment(investmentText);

		int x = 5; // month
		int p = 6; // produce rate
		int d = 6; // intern fee
		int B = 100; // cost of car
		int t = 2; // tax ratassadasd
		int c = 6; // number of investment companies

		System.out.println("month: " + x + " prate :" + p + " internfee: " + d + " carcost:" + B + " tax rate:" + t
				+ " company count:" + c);

		System.out.println("Dynamic Programming Result: " + DP(x, p, d, B, t, c));
		System.out.println("Greedy Result: " + Greedy(x, p, d, B, t, c));

//		readtest();

	}
	public static class investment {
		private int c1;
		private int c2;
		private int c3;
		private int c4;
		private int c5;
		private int c6;
		private int bestvalthatmonth;

		public investment(int c1, int c2, int c3, int c4, int c5, int c6) {
			this.c1 = c1;
			this.c2 = c2;
			this.c3 = c3;
			this.c4 = c4;
			this.c5 = c5;
			this.c6 = c6;
			bestvalthatmonth = bestratethatmonth();
		}

		public investment(int c1, int c2) {
			this.c1 = c1;
			this.c2 = c2;
			this.c3 = 0;
			this.c4 = 0;
			this.c5 = 0;
			this.c6 = 0;
			bestvalthatmonth = bestratethatmonth();
		}

		public investment(int c1, int c2, int c3) {
			this.c1 = c1;
			this.c2 = c2;
			this.c3 = c3;
			this.c4 = 0;
			this.c5 = 0;
			this.c6 = 0;
			bestvalthatmonth = bestratethatmonth();
		}

		public investment(int c1, int c2, int c3, int c4) {
			this.c1 = c1;
			this.c2 = c2;
			this.c3 = c3;
			this.c4 = c4;
			this.c5 = 0;
			this.c6 = 0;
			bestvalthatmonth = bestratethatmonth();
		}

		public investment(int c1, int c2, int c3, int c4, int c5) {
			this.c1 = c1;
			this.c2 = c2;
			this.c3 = c3;
			this.c4 = c4;
			this.c5 = c5;
			this.c6 = 0;
			bestvalthatmonth = bestratethatmonth();
		}

		public int getC1() {
			return c1;
		}

		public int getC2() {
			return c2;
		}

		public int getC3() {
			return c3;
		}

		public int getC4() {
			return c4;
		}

		public int getC5() {
			return c5;
		}

		public int getC6() {
			return c6;
		}

		public void setC1(int c1) {
			this.c1 = c1;
		}

		public void setC2(int c2) {
			this.c2 = c2;
		}

		public void setC3(int c3) {
			this.c3 = c3;
		}

		public void setC4(int c4) {
			this.c4 = c4;
		}

		public void setC5(int c5) {
			this.c5 = c5;
		}

		public void setC6(int c6) {
			this.c6 = c6;
		}

		public int getBestvalthatmonth() {
			return bestvalthatmonth;
		}

		public void setBestvalthatmonth(int bestvalthatmonth) {
			this.bestvalthatmonth = bestvalthatmonth;
		}

		public int bestratethatmonth() {
			int max = 0;
			List<Integer> maxx = new ArrayList<Integer>();
			maxx.add(c1);
			maxx.add(c2);
			maxx.add(c3);
			maxx.add(c4);
			maxx.add(c5);
			maxx.add(c6);
			Collections.sort(maxx, Collections.reverseOrder());
			max = maxx.get(0);
			return max;

		}

		public String print() {
			return String.valueOf(c1) + ", " + String.valueOf(c2) + ", " + String.valueOf(c3) + ", "
					+ String.valueOf(c4) + ", " + String.valueOf(c5) + ", " + String.valueOf(c6) + ", "
					+ String.valueOf(bestvalthatmonth);
		}
	}

	public static void readtest() {
		System.out.println(customerDemands);
		System.out.println(garageCost);
		for (int i = 0; i < investmentCost.size(); i++) {
			Object lol = investmentCost.get(i);
			System.out.println(((investment) lol).print());
		}
		for (int i = 0; i < investmentCost.size(); i++) {
			Object lol = investmentCost.get(i);
			System.out.print("m" + i + ":");
			System.out.println(((investment) lol).bestvalthatmonth);
		}

//		readingTest

	}
}
