using UnityEngine;
using System.Collections;

public class BallBehaviour : MonoBehaviour {
	
	private PaddleCollider paddle ;
	private Vector3 PaddleToBallVector;
	bool HasStarted = false;
	
	// Use this for initialization
	void Start () {
		paddle = GameObject.FindObjectOfType<PaddleCollider>();
		PaddleToBallVector = this.transform.position - paddle.transform.position;
		
	}
	
	void OnCollisionEnter2D(Collision2D collision){
		Vector2 tweak = new Vector2(Random.Range(0f,0.4f),Random.Range(0f,0.4f));
		if (HasStarted==true) audio.Play();
		rigidbody2D.velocity+= tweak;
		
	}
	
	// Update is called once per frame
	void Update () {
		
		if (!HasStarted){
			this.transform.position = paddle.transform.position + PaddleToBallVector;
		
			if (Input.GetKey(KeyCode.Space)){
					HasStarted = true;
					
					this.rigidbody2D.velocity = new Vector2(Random.Range(-2f,2f) ,10f);
			}		
		}
	}

}
