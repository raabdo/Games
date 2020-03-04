using UnityEngine;
using System.Collections;

public class LevelManager : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	
	public void NextLevel(){
		Application.LoadLevel(Application.loadedLevel+1);
	}
	
	public void LoadLevel(string name){
		Application.LoadLevel(name);
	}
	public void QuitManager(){
		Application.Quit();
	}
}
