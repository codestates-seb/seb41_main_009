import styled from 'styled-components';
import { Viewer } from '@toast-ui/react-editor';
import '@toast-ui/editor/dist/toastui-editor.css';

import { useRef, useEffect } from 'react';
import { ParagraphMedium, HeadingLarge, HeadingSmall, HeadingMedium } from '../../../styles/typo';
// import { PostDummy } from '../../../constants/dummyData';

const Container = styled.div`
  box-sizing: border-box;
  display: flex;
  width: calc(100% / 12 * 10);
  height: fit-content;
  gap: 10px;
  margin-bottom: 10px;

  .toastui-editor-contents h1 {
    // 본문 H1 태그 스타일 지정
    padding-left: 5px;
    margin-top: 30px;
    ${HeadingLarge}
    color: var(--gray-900);
    border-bottom: none;

    &:hover {
      color: var(--gray-700);
    }
  }
  .toastui-editor-contents h2 {
    // 본문 H2 태그 스타일 지정
    padding-left: 5px;
    margin-top: 20px;
    ${HeadingMedium}
    color: var(--gray-800);
    border-bottom: none;

    &:hover {
      color: var(--gray-700);
    }
  }

  .toastui-editor-contents h3 {
    // 본문 H3s 태그 스타일 지정
    padding-left: 5px;
    margin-top: 15px;
    border-bottom: none;

    color: var(--gray-800);

    ${HeadingSmall}

    &:hover {
      color: var(--gray-700);
    }
  }

  .toastui-editor-contents p {
    // 본문 P 태그 스타일 지정
    width: 100%;
    margin: 0px;
    color: var(--gray-600);

    ${ParagraphMedium}
  }
  .toastui-editor-contents img {
    // 본문 P 태그 스타일 지정
    width: 100%;
    height: 100%;
    margin: 0px;
  }
`;

const PostContentViewer = ({ post }) => {
  const viewerRef = useRef();

  useEffect(() => {
    viewerRef.current.getInstance().setMarkdown(post.content);
  }, [post]);

  return (
    <Container>
      <Viewer ref={viewerRef} initialValue={post?.content} />
    </Container>
  );
};

export default PostContentViewer;
