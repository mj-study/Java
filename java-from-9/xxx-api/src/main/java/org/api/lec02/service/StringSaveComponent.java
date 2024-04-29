package org.api.lec02.service;

import org.domain.service.MemoryStringRepository;
import org.domain.service.StringRepository;

public class StringSaveComponent {
	private final MemoryStringRepository memoryStringRepository = new MemoryStringRepository();
	private final StringRepository stringRepositoryLoader = StringRepositoryLoader.getDefault();

	public void mainLogic() {
		memoryStringRepository.save("문자열");
	}

	public static void main(String[] args) {
		StringSaveComponent component = new StringSaveComponent();
		component.mainLogic();
	}
}
