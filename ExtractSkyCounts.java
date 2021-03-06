public class ExtractSkyCounts {

	public static void main(String[] args) {
		
		// Input Manually ***********************************
		double RefStar_Counts_v = 4348815.282;
		double RefStar_Mag_v = 6.572;
		double exposure_time_v = 20;
		double RefStar_Counts_b = 4174635.723;
		double RefStar_Mag_b = 6.570;
		double exposure_time_b = 30;
		double cluster_distance = 300;
		// **************************************************
		
		double RefStar_flux_v = RefStar_Counts_v / exposure_time_v;
		double RefStar_flux_b = RefStar_Counts_b / exposure_time_b;
		double Abs_convertion_factor = Math.pow(cluster_distance/10, 2);
		
		
		String string1 = "Inset row 1";
		String string2 = "Insert v data";
		String string3 = "Insert b data";
		String[] parts1 = string1.split("	");
		String[] parts2 = string2.split("	");
		String[] parts3 = string3.split("	");
		double[] skyflux_v = new double[parts1.length];
		double[] skyflux_b = new double[parts1.length];
		double[] RA_degs = new double[parts1.length];
		double[] DEC_degs = new double[parts1.length];
		
		//Just some error checks before running loops...
		if(parts1.length != parts2.length){System.out.println("string1 does not have the same number of cells as string2!"); return;}
		if(parts3.length != parts2.length){System.out.println("string2 does not have the same number of cells as string3!"); return;}
		if(parts1.length < 5){System.out.println("Row 1 has not been copied to string 1!"); return;}
		if(parts2.length < 5){System.out.println("v data has not been copied to string 2!"); return;}
		if(parts3.length < 5){System.out.println("b data has not been copied to string 3!"); return;}
		
		
		System.out.println("Star" + "	" + "RA" + "	" + "DEC" + "	" + "Sky Counts V" + "	" + "Sky Counts B" + "	" + "Sky Flux V" + "	" + "Sky Flux B" + "	" + "mag V" + "	" + "mag B" + "	" + "B-V" + "	" + "Absolute Mag V");
		
		for (int i = 0; i < parts1.length; i++) {
			if(parts1[i].matches("Source-Sky.*")){
				skyflux_v[i] = Double.parseDouble(parts2[i])/exposure_time_v;
				skyflux_b[i] = Double.parseDouble(parts3[i])/exposure_time_b;
				RA_degs[i] = 360*Double.parseDouble(parts2[i-2])/24;
				DEC_degs[i] = Double.parseDouble(parts2[i-1]);
				System.out.print(parts1[i] + "	" + RA_degs[i] + "	" + DEC_degs[i] + "	" + parts2[i] + "	" + parts3[i] + "	");
				System.out.print(skyflux_v[i] + "	" + skyflux_b[i] + "	");
				System.out.print(RefStar_Mag_v - 2.5*Math.log10(skyflux_v[i]/RefStar_flux_v) + "	");
				System.out.print(RefStar_Mag_b - 2.5*Math.log10(skyflux_b[i]/RefStar_flux_b) + "	");
				System.out.print(RefStar_Mag_b - 2.5*Math.log10(skyflux_b[i]/RefStar_flux_b) - (RefStar_Mag_v - 2.5*Math.log10(skyflux_v[i]/RefStar_flux_v)) + "	");
				System.out.println(RefStar_Mag_v - 2.5*Math.log10(skyflux_v[i]*Abs_convertion_factor/RefStar_flux_v) );
			}}


		    }

		}
	
