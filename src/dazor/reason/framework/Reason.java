package dazor.reason.framework;

public interface Reason {

	public static Reason create() {
		return new Engine();
	}
	
	void loop();
	
	default void loop(int interval, Runnable logic) {
		setIntervall(interval);
		setRender(() -> {});
		setLogic(logic);
		loop();
	}

	default void loop(int interval, Runnable logic, Runnable render) {
		setIntervall(interval);
		setRender(render);
		setLogic(logic);
		loop();
	}
	
	void stop();

	void tick();
	
	default void tick(int interval, Runnable logic) {
		setIntervall(interval);
		setRender(() -> {});
		setLogic(logic);
		tick();
	}
	
	default void tick(int interval, Runnable logic, Runnable render) {
		setIntervall(interval);
		setRender(render);
		setLogic(logic);
		tick();
	}
	
	void setIntervall(int interval);
	
	int getIntervall();

	long getDelta();

	void setDelta(long delta);

	void setLogic(Runnable logic);
	
	void setRender(Runnable render);
}
