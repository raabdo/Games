using UnityEngine;
using System.Collections;

public class Behaviour : MonoBehaviour {
	
	// Use this for initialization
	public void LevelManager(string name){
		Application.LoadLevel(name);
		
	}
	public void LoadNextLevel(){
		
		Application.LoadLevel(Application.loadedLevel + 1);
	}
	
	public void QuitManager(){
		Application.Quit();
	}
	public void BrickDestroyed(){
		if (Brick.NumberOfBrick <=0) LevelManager("Win");
	}
	
}
