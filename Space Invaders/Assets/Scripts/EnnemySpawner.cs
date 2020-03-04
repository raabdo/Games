using UnityEngine;
using System.Collections;

public class EnnemySpawner : MonoBehaviour {
	
	
	public GameObject Ennemy, Ennemy2, Boss;
	public float width, height, speed, SpawnDelay;
	private bool moveRight = true;
	private float xmin, xmax;
	// Use this for initialization
	void Start () {
		//Spawning enemies
		SpawnEnnemies1b1();
		float distanceToCamera = transform.position.z - Camera.main.transform.position.z;
		Vector3 LeftBoundary = Camera.main.ViewportToWorldPoint(new Vector3(0,0,distanceToCamera)) ;
		Vector3 RightBoundary = Camera.main.ViewportToWorldPoint(new Vector3(1,0,distanceToCamera));
		xmax = RightBoundary.x; 
		xmin = LeftBoundary.x;
	}
	
	public void OnDrawGizmos(){
		Gizmos.DrawWireCube(transform.position, new Vector3(width,height));
	}
	void Move(){
		
		//transform.position += new Vector3(-speed*Time.deltaTime,0,0);
		if (moveRight){
			transform.position += Vector3.right*speed*Time.deltaTime;
		}
		else {
			transform.position += Vector3.left*speed*Time.deltaTime;
		}
		
	}
	void DirectBoss(){
		float a = Random.value; 
		if (a>0.5) moveRight = false;
		else moveRight = true;
	}
	void MoveBoss(){
		
		
		if (moveRight){
			transform.position += Vector3.right*speed*Time.deltaTime;
		}
		else {
			transform.position += Vector3.left*speed*Time.deltaTime;
		}
		
	}
	void AutoDirect(){
		float rightEdgeOfFormation = transform.position.x + (0.5f*width);
		float leftEdgeOfFormation = transform.position.x - (0.5f*width);
		
		if(leftEdgeOfFormation-(0.5*width)<xmin || rightEdgeOfFormation+(0.5*width)> xmax){
			moveRight= !moveRight;
		}
	}
	
	
	public bool AllMembersDead(){
		
		foreach(Transform childPositionGameObject in transform){
		if (childPositionGameObject.childCount>0) return false;
		}
		return true;
	}
	
//	void SpawnEnnemies(){
//		foreach(Transform child in transform){
//			GameObject Enemy = Instantiate(Ennemy, child.transform.position,Quaternion.identity) as GameObject;
//			Enemy.transform.parent = child ;
//		}
//	
//	}
	void SpawnEnnemies1b1(){
		Transform freePos = NextEmptyPosition();
		if (freePos) {
			
			GameObject enemy = Instantiate (Ennemy, freePos.position, Quaternion.identity) as GameObject;
			enemy.transform.parent = freePos;
		}
		
		
		if ( NextEmptyPosition()){
			Invoke("SpawnEnnemies1b1", SpawnDelay);
		}
				
	}
	void SpawnEnnemies1b1M6(){
		Transform freePos = NextEmptyPosition();
		if (freePos) {
			if (Score.score<=2000 && Score.score >1000){
				GameObject enemy = Instantiate (Ennemy2, freePos.position, Quaternion.identity) as GameObject;
				enemy.transform.parent = freePos;
			}
		}
		if (NextEmptyPosition()){
				Invoke("SpawnEnnemies1b1M6", SpawnDelay);
			}	
	}
	
	void SpawnBoss(){
	
		Transform freePos = NextEmptyPosition();
		if (freePos) {
			if (AllMembersDead()){
				GameObject enemy = Instantiate (Boss, freePos.position, Quaternion.identity) as GameObject;
				enemy.transform.parent = freePos;
			}
		}
		if (NextEmptyPosition()){
			Invoke("SpawnBoss", SpawnDelay);
		}	
		
	}
	
	Transform NextEmptyPosition(){
		
			foreach(Transform childPositionGameObject in transform) {
				if (childPositionGameObject.childCount ==0 ){
					return childPositionGameObject;
				}
			}
			
		
		
		return null;
	}
	
	
	// Update is called once per frame
	void Update () {
		Move();
		AutoDirect();
		
		if (Score.score <= 1000){
			SpawnEnnemies1b1();
		}
		else if(Score.score >1000 || Score.score<=2000){
			SpawnEnnemies1b1M6();
		}
		else if (Score.score>2000)SpawnBoss();
		
		DirectBoss();
	}
}