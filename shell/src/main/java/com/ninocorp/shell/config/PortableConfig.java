package com.ninocorp.shell.config;

import com.ninocorp.shell.portable.HabitFileStorage;
import com.ninocorp.shell.portable.StorageProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({StorageProperties.class, HabitFileStorage.class})
public class PortableConfig {
}
