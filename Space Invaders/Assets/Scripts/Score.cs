using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class Score : MonoBehaviour {

	// Use this for initialization
	
	public static int score = 0;
	public static int highscore;
	private Text text ;

	public void Scorefct(int Value){
		score += Value;
		if (score > highscore) highscore = score;
		text.text = score.ToString();
	}
	
	public static void Reset(){
		score = 0;
	}
	
	void Start(){
		text = GetComponent<Text>();
	}
	
	
	
	
}
