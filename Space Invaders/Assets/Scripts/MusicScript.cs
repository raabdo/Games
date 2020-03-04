using UnityEngine;
using System.Collections;

public class MusicScript : MonoBehaviour {
	
	static MusicScript Instance = null;
	
	void Awake() {
	
		if (Instance != null) GameObject.Destroy(gameObject);
		else {
			Instance = this;
			GameObject.DontDestroyOnLoad(gameObject);
			
		}
	}
	
	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
