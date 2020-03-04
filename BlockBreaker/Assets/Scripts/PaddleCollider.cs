using UnityEngine;
using System.Collections;

public class PaddleCollider : MonoBehaviour {

	// Use this for initialization
	public Vector3 MousePos = new Vector3 (0,-6.8f,0);
	public bool autoPlay = false;
	private BallBehaviour ball;
	
	void Start () {
		ball= GameObject.FindObjectOfType<BallBehaviour>();			
	}
	
	void OnCollisionEnter2D (Collision2D collision){ 
		
	}
	void AutoPlay() {
		Vector3 ballPosition = ball.transform.position;
		MousePos.x =  Mathf.Clamp(ballPosition.x, -9f, 9f);
		
		this.transform.position = MousePos;
	}
	
	void Move(){
	
		if (Input.GetKey(KeyCode.LeftArrow)) MousePos.x =  Mathf.Clamp(MousePos.x - 0.22f, -9f, 9f);
		if (Input.GetKey(KeyCode.RightArrow)) MousePos.x = Mathf.Clamp(MousePos.x + 0.22f, -9f, 9f);
		this.transform.position = MousePos;
		
	}
	// Update is called once per frame
	void Update () {
		
		//float MouseX = Input.mousePosition.x/Screen.width*16;
		//print(MouseX);
		//this.transform.position = MousePos(MouseX,-6.8f,0); 
		
		
		if (!autoPlay){
			Move();
		}else AutoPlay();
	}
}
