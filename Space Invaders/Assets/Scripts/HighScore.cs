using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class HighScore : MonoBehaviour {

	// Use this for initialization
	public Text txt;
	
	
	void Start () {
		//if (Score.score != null || Score.highscore != null){
		txt.text = "Your Score is: "+Score.score.ToString()+"\n"+
			"Highscore is: "+Score.highscore.ToString();		
		//}
	}
	
	// Update is called once per frame
	void Update () {
		Score.Reset();
	}
}
