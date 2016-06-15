package com.github.alexthe666.archipelago.classtransformer;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArchipelagoClassTransformer implements IClassTransformer {
	@Override
	public byte[] transform(String name, String transformedName, byte[] classBytes) {
		boolean obf;
		ClassNode classNode = new ClassNode();
		if ((obf = "bfk".equals(name)) || "net.minecraft.client.renderer.EntityRenderer".equals(name)) {
			ClassReader classReader = new ClassReader(classBytes);
			classReader.accept(classNode, 0);
			String setupFogName = obf ? "a" : "setupFog";
			String setupFogDesc = "(IF)V";
			for (MethodNode methodNode : classNode.methods) {
				if (setupFogName.equals(methodNode.name) && setupFogDesc.equals(methodNode.desc)) {
					InsnList inject = new InsnList();
					List<AbstractInsnNode> nodesInLine = new ArrayList<AbstractInsnNode>();
					for (AbstractInsnNode node : methodNode.instructions.toArray()) {
						boolean target = false;
						if (node instanceof LabelNode) {
							nodesInLine.add(node);
							for (AbstractInsnNode lineNode : nodesInLine) {
								if (lineNode instanceof MethodInsnNode) {
									MethodInsnNode method_0 = (MethodInsnNode) lineNode;
									if ((method_0.name.equals("getRespirationModifier") && method_0.owner.equals("net/minecraft/enchantment/EnchantmentHelper")) || (method_0.name.equals("a") && method_0.owner.equals("ack"))) {
										target = true;
									}
								}
							}
							if (target) {
								for (AbstractInsnNode lineNode : nodesInLine) {
									if (!(lineNode instanceof LabelNode)) {
										inject.remove(lineNode);
									}
									if (lineNode instanceof JumpInsnNode) {
										break;
									}
								}
							}
							nodesInLine.clear();
						}
						if (node.getOpcode() == Opcodes.RETURN) {
							inject.add(new VarInsnNode(Opcodes.ALOAD, 3));
							inject.add(new VarInsnNode(Opcodes.ALOAD, 4));
							inject.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "com/github/alexthe666/archipelago/classtransformer/ArchipelagoHooks", "renderUnderwaterFog", "(L" + (obf ? "rr" : "net/minecraft/entity/Entity") + ";L" + (obf ? "arc" : "net/minecraft/block/state/IBlockState") + ";)V", false));
						}
						if (!target) {
							inject.add(node);
							nodesInLine.add(node);
						}
					}
					methodNode.instructions.clear();
					methodNode.instructions.add(inject);
					break;
				}
			}
			ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			classNode.accept(classWriter);
			saveBytecode(name, classWriter);
			return classWriter.toByteArray();
		} else if ((obf = "bqf".equals(name)) || "net.minecraft.client.renderer.chunk.RenderChunk".equals(name)) {
			ClassReader classReader = new ClassReader(classBytes);
			classReader.accept(classNode, 0);
			String rebuildChunkName = obf ? "b" : "rebuildChunk";
			String rebuildChunkDesc = "(FFFL" + (obf ? "bpz" : "ChunkCompileTaskGenerator") + ";)V";
			for (MethodNode methodNode : classNode.methods) {
				if (methodNode.name.equals(rebuildChunkName) || methodNode.desc.equals(rebuildChunkDesc)) {
					InsnList inject = new InsnList();
					int i = 0;
					for (AbstractInsnNode node : methodNode.instructions.toArray()) {
						inject.add(node);
						if (node instanceof MethodInsnNode && node.getOpcode() == Opcodes.INVOKEVIRTUAL) {
							MethodInsnNode methodInsnNode = (MethodInsnNode) node;
							if (methodInsnNode.owner.equals("java/util/concurrent/locks/ReentrantLock") && methodInsnNode.name.equals("lock") && methodInsnNode.desc.equals("()V")) {
								if (i > 0) {
									inject.add(new VarInsnNode(Opcodes.ALOAD, 5));
									inject.add(new VarInsnNode(Opcodes.ALOAD, 0));
									inject.add(new FieldInsnNode(Opcodes.GETFIELD, obf ? "bqc" : "net/minecraft/client/renderer/chunk/RenderChunk", obf ? "r" : "region", "L" + (obf ? "ahx" : "net/minecraft/world/IBlockAccess") + ";"));
									inject.add(new VarInsnNode(Opcodes.ALOAD, 7));
									inject.add(new VarInsnNode(Opcodes.ALOAD, 8));
									String blockposName = obf ? "cj" : "net/minecraft/util/math/BlockPos";
									inject.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "com/github/alexthe666/archipelago/classtransformer/ArchipelagoHooks", "rebuildChunk", "(L" + (obf ? "bqc" : "net/minecraft/client/renderer/chunk/CompiledChunk") + ";L" + (obf ? "ahx" : "net/minecraft/world/IBlockAccess") + ";L" + blockposName + ";L" + blockposName + ";)V", false));
								}
								i++;
							}
						}
					}
					methodNode.instructions.clear();
					methodNode.instructions.add(inject);
				}
			}
			ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			classNode.accept(classWriter);
			saveBytecode(name, classWriter);
			return classWriter.toByteArray();
		} else if ((obf = "bno".equals(name)) || "net.minecraft.client.renderer.RenderGlobal".equals(name)) {
			ClassReader classReader = new ClassReader(classBytes);
			classReader.accept(classNode, 0);
			String renderEntitiesName = obf ? "a" : "renderEntities";
			String renderEntitiesDesc = "(L" + (obf ? "rr" : "net/minecraft/entity/Entity") + ";L" + (obf ? "bqm" : "net/minecraft/client/renderer/culling/ICamera") + ";F)V";
			for (MethodNode methodNode : classNode.methods) {
				if (methodNode.name.equals(renderEntitiesName) || methodNode.desc.equals(renderEntitiesDesc)) {
					InsnList inject = new InsnList();
					for (AbstractInsnNode node : methodNode.instructions.toArray()) {
						inject.add(node);
						if (node instanceof MethodInsnNode && node.getOpcode() == Opcodes.INVOKEVIRTUAL) {
							MethodInsnNode methodInsnNode = (MethodInsnNode) node;
							if (methodInsnNode.owner.equals(obf ? "bpm" : "net/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher") && methodInsnNode.name.equals("drawBatch") && methodInsnNode.desc.equals("(I)V")) {
								inject.add(new VarInsnNode(Opcodes.ALOAD, 2));
								inject.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "com/github/alexthe666/archipelago/classtransformer/ArchipelagoHooks", "drawSpecialRenderers", "(L" + (obf ? "bqm" : "net/minecraft/client/renderer/culling/ICamera") + ";)V", false));
							}
						}
					}
					methodNode.instructions.clear();
					methodNode.instructions.add(inject);
				}
			}
			ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			classNode.accept(classWriter);
			saveBytecode(name, classWriter);
			return classWriter.toByteArray();
		}
		return classBytes;
	}

	private void saveBytecode(String name, ClassWriter cw) {
		try {
			File debugDir = new File("archipelago/debug/");
			debugDir.mkdirs();
			FileOutputStream out = new FileOutputStream(new File(debugDir, name + ".class"));
			out.write(cw.toByteArray());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
