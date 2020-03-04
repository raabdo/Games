using UnityEngine;
using System.Collections;

public class LoseCollider : MonoBehaviour {
	
	private Behaviour LevelManager;
	// Use this for initialization
	void Start () {
		
	}
	void OnCollisionEnter2D (Collision2D collision){
		LevelManager = FindObjectOfType<Behaviour>();
		LevelManager.LevelManager("Lose");
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
