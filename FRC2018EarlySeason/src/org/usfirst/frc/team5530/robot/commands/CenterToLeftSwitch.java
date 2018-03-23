package org.usfirst.frc.team5530.robot.commands;

import org.usfirst.frc.team5530.robot.Robot;
import org.usfirst.frc.team5530.robot.triggers.*;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc.team5530.robot.subsystems.ArmSS;
import org.usfirst.frc.team5530.robot.subsystems.DrivetrainSS;
import org.usfirst.frc.team5530.robot.subsystems.ElevatorSS;
import org.usfirst.frc.team5530.robot.triggers.*;

import edu.wpi.first.wpilibj.command.Command;

public class CenterToLeftSwitch extends Command{
	public MotionProfileExample MPR; 
	public MotionProfileExample MPL;
	private double[][] profileRight = {{0,0.17133595,0.342030767,0.51210598,0.681583106,0.850483644,1.018829067,1.186640816,1.353940291,1.520748846,1.68708778,1.852978334,2.018441677,2.183498905,2.348171032,2.512478982,2.676443584,2.840085563,3.003425535,3.166483999,3.329281332,3.491837778,3.654173449,3.816308311,3.978262181,4.140054724,4.301705442,4.463233667,4.624658564,4.785999115,4.947274119,5.108502188,5.269701737,5.430890983,5.592087939,5.75331041,5.914575985,6.075902039,6.237305725,6.398803969,6.560413469,6.722150692,6.884031868,7.046072988,7.208289802,7.370697816,7.533312288,7.696148229,7.859220397,8.022543298,8.186131183,8.349998047,8.514157628,8.678623404,8.843408595,9.008526159,9.173988796,9.339808941,9.505998771,9.672570199,9.839534878,10.0069042,10.1746893,10.34290104,10.51155004,10.68064665,10.85020096,11.02022282,11.19072181,11.36170727,11.53318827,11.70517364,11.87767196,12.05069156,12.22424055,12.39832675,12.57295779,12.74814103,12.92388359,13.10019239,13.27707408,13.45453511,13.63258169,13.81121982,13.99045526,14.17029356,14.35074007,14.5317999,14.71347798,14.89577902,15.07870752,15.26226778,15.44646393,15.63129987,15.81677933,16.00290583,16.18968275,16.37711323,16.56520027},
			{0,17.13359503,17.06948167,17.00752125,16.94771259,16.8900538,16.83454233,16.78117489,16.72994751,16.6808555,16.63389347,16.58905534,16.54633429,16.50572281,16.46721269,16.43079502,16.3964602,16.36419792,16.33399722,16.30584643,16.27973323,16.25564465,16.23356705,16.21348616,16.19538709,16.17925431,16.16507171,16.15282259,16.14248965,16.13405507,16.12750045,16.12280687,16.1199549,16.11892462,16.11969562,16.12224703,16.12655754,16.13260543,16.14036856,16.1498244,16.16095005,16.1737223,16.18811757,16.20411198,16.22168139,16.24080136,16.26144723,16.28359408,16.3072168,16.33229011,16.35878852,16.38668643,16.41595808,16.44657761,16.47851907,16.51175644,16.54626363,16.58201452,16.61898297,16.65714283,16.69646796,16.73693225,16.77850966,16.82117416,16.86489985,16.90966087,16.9554315,17.00218612,17.04989923,17.09854549,17.14809971,17.19853686,17.2498321,17.30196076,17.35489838,17.4086207,17.46310367,17.51832348,17.57425656,17.63087954,17.68816934,17.74610312,17.80465828,17.86381252,17.92354379,17.98383032,18.04465062,18.10598349,18.16780802,18.23010357,18.29284982,18.35602674,18.41961459,18.48359394,18.54794566,18.61265091,18.67769119,18.74304826,18.80870422},
			{10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10}};
	private double[][] profileLeft = {{0,0.3280675092,0.6556021995,0.982608491,1.309090705,1.635053068,1.960499715,2.28543469,2.609861954,2.933785383,3.257208776,3.580135854,3.902570266,4.224515595,4.545975353,4.866952994,5.18745191,5.507475439,5.827026868,6.146109432,6.464726322,6.782880688,7.100575639,7.41781425,7.734599561,8.050934586,8.366822309,8.682265693,8.997267679,9.311831192,9.62595914,9.93965442,10.25291992,10.56575852,10.87817309,11.19016652,11.50174166,11.81290139,12.12364861,12.43398618,12.74391699,13.05344397,13.36257,13.67129802,13.97963097,14.2875718,14.59512348,14.90228899,15.20907135,15.51547356,15.82149869,16.12714978,16.43242992,16.73734222,17.0418898,17.34607581,17.64990341,17.9533758,18.25649618,18.55926779,18.86169388,19.16377772,19.46552261,19.76693185,20.06800879,20.36875675,20.66917912,20.96927927,21.26906061,21.56852654,21.86768048,22.16652589,22.4650662,22.76330488,23.0612454,23.35889123,23.65624586,23.95331277,24.25009545,24.54659741,24.84282213,25.13877312,25.43445386,25.72986785,26.02501858,26.31990951,26.61454413,26.9089259,27.20305827,27.49694468,27.79058856,28.08399333,28.37716237,28.67009907,28.9628068,29.25528888,29.54754863,29.83958935,30.13141431},
			{0,32.80675092,32.75346904,32.70062915,32.64822141,32.59623632,32.54466466,32.49349753,32.44272637,32.39234291,32.34233925,32.29270779,32.24344129,32.19453283,32.14597583,32.09776406,32.04989162,32.00235296,31.95514284,31.90825639,31.86168904,31.81543659,31.76949512,31.72386106,31.67853116,31.63350245,31.58877231,31.54433839,31.50019863,31.45635127,31.41279481,31.36952804,31.32654998,31.28385993,31.24145741,31.19934218,31.15751422,31.11597374,31.0747211,31.03375692,30.99308193,30.95269708,30.91260345,30.87280229,30.83329495,30.79408293,30.75516785,30.7165514,30.67823539,30.6402217,30.60251227,30.56510911,30.52801427,30.49122984,30.45475795,30.41860071,30.38276027,30.34723877,30.31203833,30.27716106,30.24260902,30.20838424,30.17448873,30.1409244,30.10769313,30.07479672,30.04223688,30.01001527,29.97813343,29.94659281,29.91539478,29.88454057,29.85403132,29.82386806,29.79405168,29.76458297,29.73546257,29.70669099,29.67826864,29.65019575,29.62247244,29.59509867,29.56807428,29.54139894,29.51507221,29.48909347,29.46346197,29.43817681,29.41323695,29.3886412,29.36438822,29.34047652,29.31690448,29.29367033,29.27077216,29.2482079,29.22597536,29.20407221,29.18249599},
			{10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10}};
	
	public CenterToLeftSwitch() {
		requires(Robot.drivetrainSS);
		MPR = new MotionProfileExample(DrivetrainSS.frontRight, profileRight);
		MPL = new MotionProfileExample(DrivetrainSS.frontLeft, profileLeft);
	}
	
	protected void initialize() {
		DrivetrainSS.setFollowing();
		DrivetrainSS.frontRight.set(ControlMode.MotionProfile, 0);
		DrivetrainSS.frontLeft.set(ControlMode.MotionProfile, 0);
		MPR.startMotionProfile();
		MPL.startMotionProfile();
		DrivetrainSS.frontRight.config_kP(0, .01, 0);
		DrivetrainSS.frontRight.config_kI(0, 0, 0);
		DrivetrainSS.frontRight.config_kD(0, 0, 0);
		DrivetrainSS.frontLeft.config_kP(0, .01, 0);
		DrivetrainSS.frontLeft.config_kI(0, 0, 0);
		DrivetrainSS.frontLeft.config_kD(0, 0, 0);
		
	}

	protected void execute() {
		MPR.control();
		MPL.control();
	}
	protected boolean isFinished() {
		return false;
	}
	protected void end() {
		
	}
	protected void interrupted() {
		
	}
}
