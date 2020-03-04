using UnityEngine;
using System.Collections;

public class EnemyBehaviour : MonoBehaviour {
	public GameObject projectile;
	public float LaserSpeed;
	public float health;
	public float shotpersecond ;
	public int value;
	private Score score;
	public AudioClip Death, FireSound;
	// Use this for initialization
	void Start(){
		score = GameObject.FindObjectOfType<Score>();
	}
	
	void OnTriggerEnter2D(Collider2D col){
		ProjectileBehaviour Laser = col.gameObject.GetComponent<ProjectileBehaviour>();
		if (Laser){
			health -= Laser.GetDamage(); 
			Laser.Hit();
			if (health<=0) {
				Destroy(gameObject);
				score.Scorefct(value);	
				AudioSource.PlayClipAtPoint(Death, transform.position);
			}
		}	
	}
	
	void Shoot(){
		GameObject EnemyLaser = Instantiate(projectile, transform.position, Quaternion.identity)as GameObject;
		EnemyLaser.rigidbody2D.velocity = new Vector3(0,-LaserSpeed,0);
		AudioSource.PlayClipAtPoint(FireSound, transform.position);
	}
	
	
	
	void Update(){
		float probability = Time.deltaTime * shotpersecond;
		if (probability > Random.value){
				Shoot();
		}
		
	}
}
