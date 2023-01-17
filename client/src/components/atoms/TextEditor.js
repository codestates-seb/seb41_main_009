import { useRef } from 'react';
import { Editor } from '@toast-ui/react-editor';
import '@toast-ui/editor/dist/i18n/ko-kr';
import '@toast-ui/editor/dist/toastui-editor.css';
import colorSyntax from '@toast-ui/editor-plugin-color-syntax';
import 'tui-color-picker/dist/tui-color-picker.css';
import uploadImage from '../../functions/uploadImage';

const TextEditor = ({ initialValue = '내용을 입력해주세요.' }) => {
  const editorRef = useRef();

  return (
    <div style={{ width: '100%' }}>
      <Editor
        height="auto"
        initialValue={initialValue}
        initialEditType="wysiwyg"
        language="ko-KR"
        hideModeSwitch="true"
        plugins={[colorSyntax]}
        ref={editorRef}
        hooks={{
          addImageBlobHook: uploadImage,
        }}
      />
    </div>
  );
};

export default TextEditor;
