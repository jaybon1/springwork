import java.lang.reflect.Field;
import java.lang.reflect.Method;

// 스프링 로딩될 때 
// 메모리에 무엇이 떴는지 분석하는 것이 리플렉션
// 스프링에서는 톰캣실행시 / 컨트롤러 실행전 리플렉션이 작동된다
public class CosContainer {

	// <T> 매개변수에 임의의 타입을 넣을 경우
	public <T> T invokeAnnotation(T instance) {
		Method[] methods = instance.getClass().getDeclaredMethods(); // 클래스가 들고 있는 모든메서드 들고옴
		
		// 메서드 갯수 확인
		System.out.println("함수명 : "+methods[0]);
		
		for (Method method : methods) {
			if(method.isAnnotationPresent(NumCheck.class)) {
				
				// 해당 객체의 필드를 가져온다
				Field[] f = instance.getClass().getFields();
				
				try {
					
					int num1 = f[0].getInt(instance); // getInt 메모리의 주소번호가져옴
					int num2 = f[1].getInt(instance);
					
					System.out.println(num1);
					System.out.println(num2);
					
					if((num1 - num2) < 0) {
						System.out.println("금액이 부족합니다");
						return null;
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
		return instance;
	}
}
