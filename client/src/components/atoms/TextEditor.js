import { useEffect, useRef } from 'react';
import { Editor } from '@toast-ui/react-editor';
import '@toast-ui/editor/dist/i18n/ko-kr';
import '@toast-ui/editor/dist/toastui-editor.css';
import colorSyntax from '@toast-ui/editor-plugin-color-syntax';
import 'tui-color-picker/dist/tui-color-picker.css';
import uploadImage from '../../functions/uploadImage';
import scanImage from '../../functions/scanImage';

const TextEditor = ({ body, setBody, setImage }) => {
  const editorRef = useRef();

  useEffect(() => {
    editorRef.current.getInstance().setHTML(body);
    setImage(scanImage(body));
  }, [body]);

  const handleSetBody = () => {
    setBody(editorRef.current.getInstance().getHTML());
  };

  return (
    <div style={{ width: '100%' }}>
      <Editor
        height="auto"
        initialEditType="wysiwyg"
        language="ko-KR"
        hideModeSwitch="true"
        plugins={[colorSyntax]}
        ref={editorRef}
        onBlur={handleSetBody}
        hooks={{
          addImageBlobHook: uploadImage,
        }}
      />
    </div>
  );
};

export default TextEditor;
